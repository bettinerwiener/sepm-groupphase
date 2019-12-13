package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.PerformanceRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.SeatRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.SectionRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SimplePerformanceService implements PerformanceService {

    private PerformanceRepository performanceRepository;
    private TicketRepository ticketRepository;
    private SeatRepository seatRepository;
    private SectionRepository sectionRepository;

    public SimplePerformanceService(PerformanceRepository performanceRepository,
                                    TicketRepository ticketRepository, SeatRepository seatRepository,
                                    SectionRepository sectionRepository) {
        this.performanceRepository = performanceRepository;
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.sectionRepository = sectionRepository;
    }

    @Override
    public EventPerformance create(EventPerformance eventPerformance) throws NotCreatedException {
        log.info("Creating performance for event {} ...", eventPerformance.getEvent().getTitle());
        try {
            this.performanceRepository.save(eventPerformance);
            List<Ticket> tickets = new ArrayList<>();
            for (Section section : this.sectionRepository.findByRoom(eventPerformance.getRoom())) {
                log.info("Seats for section letter {}", section.getLetter());
                for (Seat seat : this.seatRepository.findBySection(section)) {
                    Ticket ticketToAdd = new Ticket();
                    ticketToAdd.setPerformance(eventPerformance);
                    ticketToAdd.setSeat(seat);
                    ticketToAdd.setStatus(Ticket.Status.AVAILABLE);
                    ticketToAdd.setPrice(19.70f);
                }
            }
            this.ticketRepository.saveAll(tickets);
            return eventPerformance;
        } catch (DataAccessException dae) {
            log.error("The performance could not be created: {}", dae.getMessage());
            throw new NotCreatedException(String.format("The performance could not be created: %s", dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> getAll() throws NotFoundException {
        log.info("Getting all performances ...");
        try {
            List<EventPerformance> eventPerformances = this.performanceRepository.findAll();
            log.info("The number of performances is {}", eventPerformances.size());
            if (eventPerformances != null && !eventPerformances.isEmpty()) {
                return eventPerformances;
            } else {
                log.error("No performances could be found");
                throw new NotFoundException(String.format("No performances could be found"));
            }
        } catch (DataAccessException dae) {
            log.error("No performances could be found: {}",
                dae.getMessage());
            throw new NotFoundException(String.format("No performances could be found: %s",
                dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> findByEvent(Long eventId) throws NotFoundException {
        log.info("Getting all performances for event {} ...", eventId);
        try {
            List<EventPerformance> eventPerformances = this.performanceRepository.findByEventId(eventId);
            if (eventPerformances != null && !eventPerformances.isEmpty()) {
                return eventPerformances;
            } else {
                log.error("No performances for the requested location {} could be found",
                    eventId);
                throw new NotFoundException(String.format("No performances for the requested location %d could be found",
                    eventId));
            }
        } catch (DataAccessException dae) {
            log.error("No performances for the requested location {} could be found: {}",
                eventId, dae.getMessage());
            throw new NotFoundException(String.format("No performances for the requested location %d could be found: %s",
                eventId, dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> findByRoom(Room room) throws NotFoundException {
        return null;
    }
}
