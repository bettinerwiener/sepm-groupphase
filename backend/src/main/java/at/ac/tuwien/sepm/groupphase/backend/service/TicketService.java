package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotSavedException;

import java.util.List;

public interface TicketService {

    /**
     * Gets all tickets belonging to an order
     * @param id of the order the tickets belong to
     * @return a list of tickets belonging to the order specified
     */
    List<Ticket> findTicketsByOrderId(Long id);
    /**
     * Creates tickets for a performance
     * @param eventPerformance tickets are created for
     * @return list of tickets created for performance
     * @throws NotCreatedException in case something goes while creating tickets
     */
    List<Ticket> createForPerformance(EventPerformance eventPerformance) throws NotCreatedException;

    /**
     * Gets all tickets
     * @return a list of all tickets currently in the database
     */
    List<Ticket> getAll();

    /**
     * Gets all tickets for a performance
     * @param performanceId for which all tickets shall be retrieved
     * @return a list of tickets retrieved for the performance specified
     */
    List<Ticket> findByPerformanceId(Long performanceId);

    /**
     * Gets a ticket by its id
     * @param id of the ticket to be returned
     * @return the ticket with the specified id
     */
    Ticket findById(Long id);

    /**
     * Gets all tickets for a performance
     * @param id for which all tickets shall be retrieved
     * @return a list of tickets retrieved for the performance specified
     * @throws NotFoundException
     */
    List<Ticket> findByPerformance(Long id) throws NotFoundException;

    Ticket update(Ticket ticket) throws NotSavedException;
}
