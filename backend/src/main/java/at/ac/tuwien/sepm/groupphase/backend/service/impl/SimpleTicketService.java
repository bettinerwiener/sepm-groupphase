package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.LocationRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleTicketService implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public SimpleTicketService() {};
    public SimpleTicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<Ticket> findTicketsByOrderId(Long id) {
        try {
            List<Ticket> tickets = this.ticketRepository.findTicketsByCustomerOrderId(id);
            if (tickets != null && !tickets.isEmpty()) {
                return tickets;
            }
            else { throw new NotFoundException("No locations have been found.");
            }
        } catch (DataAccessException dae) {
            throw new NotFoundException(String.format("No locations have been found: %s", dae.getMessage()));
        }
    }
}
