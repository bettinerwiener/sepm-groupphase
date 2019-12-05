package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.PerformanceRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class SimplePerformanceService implements PerformanceService {

    private PerformanceRepository performanceRepository;

    public SimplePerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @Override
    public EventPerformance create(Event event, Room room, Date dateTime) throws NotCreatedException {
        log.info("Creating performance for event %s ...", event.getTitle());
        try {
            EventPerformance eventPerformance = new EventPerformance();
            eventPerformance.setEvent(event);
            eventPerformance.setDate(dateTime);
            eventPerformance.setRoom(room);
            this.performanceRepository.save(eventPerformance);
            return eventPerformance;
        } catch (DataAccessException dae) {
            log.error("The performance could not be created: %s", dae.getMessage());
            throw new NotCreatedException(String.format("The performance could not be created: %s", dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> getAll() throws NotFoundException {
        log.info("Getting all performances ...");
        try {
            List<EventPerformance> eventPerformances = this.performanceRepository.findAll();
            if (eventPerformances != null && !eventPerformances.isEmpty()) {
                return eventPerformances;
            } else {
                log.error("No performances could be found");
                throw new NotFoundException(String.format("No performances could be found"));
            }
        } catch (DataAccessException dae) {
            log.error("No performances could be found: %s",
                dae.getMessage());
            throw new NotFoundException(String.format("No performances could be found: %s",
                dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> findByEvent(Event event) throws NotFoundException {
        log.info("Getting all performances for event %s ...", event.getTitle());
        try {
            List<EventPerformance> eventPerformances = this.performanceRepository.findByEvent(event);
            if (eventPerformances != null && !eventPerformances.isEmpty()) {
                return eventPerformances;
            } else {
                log.error("No performances for the requested location %s could be found",
                    event.getTitle());
                throw new NotFoundException(String.format("No performances for the requested location %d could be found",
                    event.getTitle()));
            }
        } catch (DataAccessException dae) {
            log.error("No performances for the requested location %s could be found: %s",
                event.getTitle(), dae.getMessage());
            throw new NotFoundException(String.format("No performances for the requested location %d could be found: %s",
                event.getTitle(), dae.getMessage()));
        }
    }

    @Override
    public List<EventPerformance> findByRoom(Room room) throws NotFoundException {
        return null;
    }
}
