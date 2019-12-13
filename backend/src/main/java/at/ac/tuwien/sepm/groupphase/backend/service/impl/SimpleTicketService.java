package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import org.springframework.dao.DataAccessException;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SimpleTicketService implements TicketService {

    private TicketRepository ticketRepository;

    public SimpleTicketService(TicketRepository ticketrepository) {
        this.ticketRepository = ticketrepository;
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
}
