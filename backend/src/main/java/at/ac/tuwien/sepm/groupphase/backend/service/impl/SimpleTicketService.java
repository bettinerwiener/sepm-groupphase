package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
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
    public List<Ticket> createForPerformance(EventPerformance eventPerformance) throws NotCreatedException {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return this.ticketRepository.findAll();
    }
}
