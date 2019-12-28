package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.TicketNotAvailableException;
import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

//import static at.ac.tuwien.sepm.groupphase.backend.entity.Ticket.Status.BOUGHT;

@Slf4j
@Service
public class SimpleShoppingCartService implements ShoppingCartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;


    @Autowired
    public SimpleShoppingCartService(OrderRepository orderRepository, TicketRepository ticketRepository) {
        this.orderRepository = orderRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Order BuyTickets(User user, List<Ticket> tickets)throws NotFoundException,NotCreatedException,TicketNotAvailableException {
        log.info("User " + user.getId() + " buys " + tickets.size() + " Tickets");

        Order order =new Order();
        order.setUserId(user.getId());
        try {

            for (Ticket ticket : tickets) {

                if(this.ticketRepository.findFirstById(ticket.getId())!=null){

                    Ticket ticketToBuy = this.ticketRepository.getOne(ticket.getId());

                    if (ticketToBuy.getStatus() == Ticket.Status.BOUGHT) {
                        log.error("ticket bought already",ticket);
                        throw new TicketNotAvailableException("One of the tickets you want to buy is not available");
                    }else if(ticketToBuy.getStatus()== Ticket.Status.RESERVED && this.ticketRepository.findUserIdToTicket(ticketToBuy.getId())!= user.getId()){
                        log.error("ticket reserved by other user",ticket);
                        throw new TicketNotAvailableException("One of the tickets you want to reserve is reserved by another user");
                    }
                } else {
                    log.error("ticket does not exist", ticket);
                    throw new NotFoundException("One of the tickets you want to buy doesnt exist");
                }
            }

            this.orderRepository.save(order);

            for (Ticket ticket : tickets) {

                Ticket ticketToBuy = this.ticketRepository.getOne(ticket.getId());
                ticketToBuy.setStatus(Ticket.Status.BOUGHT);
                ticketToBuy.setCustomerOrder(order);

                ticketRepository.save(ticketToBuy);
            }

        }catch (DataAccessException dae) {
            log.error("ShoppingCartService: tickets could not be bought: " + dae.getMessage());
            throw new NotCreatedException(String.format("The tickets could not be bought", dae.getMessage()));
        }
        return order;
    }

    @Override
    public Order ReserveTickets(User user, List<Ticket> tickets)throws NotFoundException,NotCreatedException,TicketNotAvailableException {
        log.info("User " + user.getId() + " reserves " + tickets.size() + " Tickets");

        List<Ticket> ticketsToOrder = new ArrayList<Ticket>() ;
        Order order =new Order();
        order.setUserId(user.getId());
        try {
            for (Ticket ticket : tickets) {

                if(this.ticketRepository.findFirstById(ticket.getId())!=null){

                    Ticket ticketToReserve = this.ticketRepository.getOne(ticket.getId());

                    if (ticketToReserve.getStatus() == Ticket.Status.BOUGHT) {
                        log.error("ticket bought already",ticket);
                        throw new TicketNotAvailableException("One of the tickets you want to reserve is not available");
                    }else if(ticketToReserve.getStatus()== Ticket.Status.RESERVED && this.ticketRepository.findUserIdToTicket(ticketToReserve.getId())!= user.getId()){
                        log.error("ticket reserved by other user",ticket);
                        throw new TicketNotAvailableException("One of the tickets you want to reserve is reserved by another user");
                    }else if(ticketToReserve.getStatus()== Ticket.Status.RESERVED && this.ticketRepository.findUserIdToTicket(ticketToReserve.getId())== user.getId()){
                        log.error("ticket reserved by same user already",ticket);
                        throw new TicketNotAvailableException("You already reserved one or more of the tickets");
                    }
                } else {
                    log.error("ticket does not exist", ticket);
                    throw new NotFoundException("One of the tickets you want to reserve doesnt exist");
                }

            }
            this.orderRepository.save(order);

            for (Ticket ticket : tickets) {

                Ticket ticketToReserve = this.ticketRepository.getOne(ticket.getId());
                ticketToReserve.setStatus(Ticket.Status.RESERVED);
                ticketToReserve.setCustomerOrder(order);

                ticketsToOrder.add(ticketToReserve);
                ticketRepository.save(ticketToReserve);

            }

        }catch (DataAccessException dae) {
            LOGGER.error("ShoppingCartService: tickets could not be reserved: " + dae.getMessage());
            throw new NotCreatedException(String.format("The tickets could not be reserved: ", dae.getMessage()));
        }
        return order;
    }

    @Override
    public Ticket CancelTicket(User user, Ticket ticket)throws NotFoundException,NotCreatedException,TicketNotAvailableException {
        log.info("User " + user.getId() + " cancels Ticket " + ticket);


        try{
            if(this.ticketRepository.findFirstById(ticket.getId())!=null){

                Ticket ticketToCancel = this.ticketRepository.getOne(ticket.getId());

                if (ticketToCancel.getStatus() == Ticket.Status.BOUGHT && this.ticketRepository.findUserIdToTicket(ticketToCancel.getId()) != user.getId()) {
                    log.error("ticket to cancel bought by another user",ticket);
                    throw new TicketNotAvailableException("Can only cancel tickets you ordered");
                }else if(ticketToCancel.getStatus()== Ticket.Status.RESERVED && this.ticketRepository.findUserIdToTicket(ticketToCancel.getId())!= user.getId()){
                    log.error("ticket to cancel reserved by other user",ticket);
                    throw new TicketNotAvailableException("Can only cancel tickets you ordered");
                }else if(ticketToCancel.getStatus()== Ticket.Status.AVAILABLE ){
                    log.error("ticket to cancel not reserved or bought by anyone",ticket);
                    throw new TicketNotAvailableException("Can only cancel tickets you ordered");
                }

                Long orderId = ticketToCancel.getCustomerOrder().getId();

                ticketToCancel.setStatus(Ticket.Status.AVAILABLE);
                ticketToCancel.setCustomerOrder(null);
                this.ticketRepository.save(ticketToCancel);
                if(this.ticketRepository.getNumberOfTicketsInOrder(orderId) == 0){
                    this.orderRepository.deleteById(orderId);
                }

            } else {
                log.error("ticket does not exist", ticket);
                throw new NotFoundException("One of the tickets you want to cancel doesnt exist");
            }

        }catch (DataAccessException dae) {
            LOGGER.error("ShoppingCartService: ticket could not be cancelled: " + dae.getMessage());
            throw new NotCreatedException(String.format("Ticket could not be cancelled: ", dae.getMessage()));
        }
        return this.ticketRepository.findFirstById(ticket.getId());
    }
}
