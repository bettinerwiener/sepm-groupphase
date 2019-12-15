package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@EnableScheduling
public class SimpleTicketService implements TicketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private TicketRepository ticketRepository;
    private OrderRepository orderRepository;

    @Autowired
    public SimpleTicketService(TicketRepository ticketrepository, OrderRepository orderRepository) {
        this.ticketRepository = ticketrepository;
        this.orderRepository= orderRepository;
    }


    @Override
    public List<Ticket> findTicketsByOrderId(Long id) {
        try {
            List<Ticket> tickets = this.ticketRepository.findTicketsByCustomerOrderId(id);
            if (tickets != null && !tickets.isEmpty()) {
                return tickets;
            } else {
                throw new NotFoundException("No locations have been found.");
            }
        } catch (DataAccessException dae) {
            throw new NotFoundException(String.format("No locations have been found: %s", dae.getMessage()));
        }
    }

    @Override
    public List<Ticket> createForPerformance(EventPerformance eventPerformance) throws NotCreatedException {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return this.ticketRepository.findAll();
    }

    @Override
    public List<Ticket> findByPerformanceId(Long performanceId) {
        try {
            return this.ticketRepository.findByPerformanceId(performanceId);
        } catch (DataAccessException dae) {
            throw new NotFoundException("No tickets found");
        }
    }

    @Scheduled(fixedDelay = 10000, initialDelay = 10000)
    protected void removeReservations() throws NotCreatedException {
        log.info("Remove Reservation from expired tickets");
        LocalDateTime currentTime = LocalDateTime.now().plus(30, ChronoUnit.MINUTES);
        List<Ticket> tickets = ticketRepository.getAllTicketsWhereReservationRunsOut(currentTime);
        System.out.println(tickets.size());

        List<Long> ordersToDelete = new ArrayList<>();
        try {
            for (Ticket ticket : tickets) {

                Long orderId = this.ticketRepository.findOrderIdforTicket(ticket.getId());
                ordersToDelete.add(orderId);

                ticket.setStatus(Ticket.Status.AVAILABLE);
                ticket.setCustomerOrder(null);
                this.ticketRepository.save(ticket);

            }

            List<Long> distinctOrders = ordersToDelete.stream()
                .distinct()
                .collect(Collectors.toList());

            String listString = distinctOrders.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
            System.out.println(listString);


            for(Long id : distinctOrders){
                this.orderRepository.deleteById(id);
            }

        }catch (DataAccessException dae) {
            LOGGER.error("SimpleTicketService: ticket reservation could not be removed: " + dae.getMessage());
            throw new NotCreatedException(String.format("The ticket reservation could not be removed: ", dae.getMessage()));
        }

        //delete orders
    }


}
