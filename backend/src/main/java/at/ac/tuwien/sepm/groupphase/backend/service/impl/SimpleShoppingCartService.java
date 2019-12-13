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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

//import static at.ac.tuwien.sepm.groupphase.backend.entity.Ticket.Status.BOUGHT;


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
        LOGGER.info("User " + user.getId() + " buys " + tickets.size() + " Tickets");

        List<Ticket> ticketsToOrder = new ArrayList<Ticket>() ;
        Order order =new Order(user.getId());
        this.orderRepository.save(order);
        try {

            for (Ticket ticket : tickets) {

                if(this.ticketRepository.findFirstById(ticket.getId())!=null){

                    Ticket ticketToBuy = this.ticketRepository.getOne(ticket.getId());

                    if (ticketToBuy.getStatus() == Ticket.Status.BOUGHT) {
                        throw new TicketNotAvailableException("One of the tickets you want to buy is not available");
                    }
                } else {
                    throw new NotFoundException("One of the tickets you want to buy doesnt exist");
                }

            }

            for (Ticket ticket : tickets) {

                Ticket ticketToBuy = this.ticketRepository.getOne(ticket.getId());
                ticketToBuy.setStatus(Ticket.Status.BOUGHT);
                ticketToBuy.setCustomer_order(order);

                ticketsToOrder.add(ticketToBuy);
                ticketRepository.save(ticketToBuy);
            }

        }catch (DataAccessException dae) {
            LOGGER.error("ShoppingCartService: tickets could not be updated: " + dae.getMessage());
            throw new NotCreatedException(String.format("The tickets could not be bought", dae.getMessage()));
        }

        order.setTickets(ticketsToOrder);
        try {

            return this.orderRepository.save(order);

        } catch (DataAccessException dae) {
            LOGGER.error("ShoppingCartService: order could not be created" + dae.getMessage());
            throw new NotCreatedException(String.format("The order  could not be created", dae.getMessage()));
        }

    }
}
