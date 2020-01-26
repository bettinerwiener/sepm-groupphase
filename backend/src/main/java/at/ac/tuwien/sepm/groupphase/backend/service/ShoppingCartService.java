package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.TicketNotAvailableException;

import java.util.List;

public interface ShoppingCartService {

    /**
     * Create a new buy Order and buy all the given tickets
     *
     * @param user making the order
     * @param tickets tickets to buy
     * @return the created order
     * @throws NotFoundException if ticket the user wants to buy doesnt exists
     * @throws NotCreatedException in case something went wrong when accessing the database
     * @throws TicketNotAvailableException in case the ticket is already bought or reserved by another user
     */
    Order BuyAvailableTickets(User user, List<Ticket> tickets);

    /**
     * Buy reserved Tickets and delete all remaining in the order
     *
     * @param user making the order
     * @param tickets tickets to buy
     * @return the created order
     * @throws NotFoundException if ticket the user wants to buy doesnt exists
     * @throws NotCreatedException in case something went wrong when accessing the database
     * @throws TicketNotAvailableException in case the ticket is already bought or reserved by another user
     */
    Order BuyReservedTickets(User user, List<Ticket> tickets);

    /**
     * Create a new reserve Order and reserve all the given tickets
     *
     * @param user making the order
     * @param tickets tickets to rserve
     * @return the created order
     * @throws NotFoundException if ticket the user wants to reserve doesnt exists
     * @throws NotCreatedException in case something went wrong when accessing the database
     * @throws TicketNotAvailableException in case the ticket is already bought or reserved
     */
    Order ReserveTickets(User user, List<Ticket> tickets);


    /**
     *
     * @param user making the cancellation
     * @param tickets ticket to cancel
     * @return the cancelled tickets
     * @throws NotFoundException if ticket the user wants to cancel doesnt exists
     * @throws NotCreatedException in case something went wrong when accessing the database
     * @throws TicketNotAvailableException in case the ticket is bought or reserved by another
     */
    List<Ticket> CancelTickets(User user, List<Ticket> tickets);

}
