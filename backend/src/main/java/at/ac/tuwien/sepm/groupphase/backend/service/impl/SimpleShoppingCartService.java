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
import java.util.List;

import static at.ac.tuwien.sepm.groupphase.backend.entity.Ticket.Status.BOUGHT;


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
    public Order BuyTickets(User user, List<Ticket> tickets)throws NotFoundException,NotCreatedException,TicketNotAvailableException{
        LOGGER.info("User " + user.getId()+ " buys " + tickets.size() +" Tickets" );

        Order order= new Order( user.getId(), tickets);

        for (Ticket ticket : tickets) {

            try {

                Ticket ticketToBuy = this.ticketRepository.getOne(ticket.getId());

                if (ticketToBuy != null) {
                    if(ticketToBuy.getStatus()==BOUGHT){

                        throw new TicketNotAvailableException("The ticket you want to buy is not available");
                    }else{
                        ticketToBuy.setStatus(BOUGHT);
                        ticketRepository.save(ticketToBuy);
                    }

                } else {
                    throw new NotFoundException("The ticket you want to buy doesnt exist");
                }

            } catch (DataAccessException dae) {
                LOGGER.error("ShoppingCartService: ticket could not be updated: " + dae.getMessage());
                throw new NotCreatedException(String.format("The ticket %s could not be bought: %s",
                    ticket.getId(), dae.getMessage()));
            }

        }

        try {

            return this.orderRepository.save(order);


        } catch (DataAccessException dae) {
            LOGGER.error("ShoppingCartService: order could not be created: " + dae.getMessage());
            throw new NotCreatedException(String.format("The order %s could not be created: %s",
                order.getId(), dae.getMessage()));
        }

    }
}
