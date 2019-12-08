package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;

import java.util.List;

public interface TicketService {

    /**
     * Creates tickets for a performance
     * @param eventPerformance tickets are created for
     * @return list of tickets created for performance
     * @throws NotCreatedException in case something goes while creating tickets
     */
    public List<Ticket> createForPerformance(EventPerformance eventPerformance) throws NotCreatedException;
}
