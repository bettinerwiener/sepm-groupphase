package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepositoryCustom;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleEventService implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private EventRepository eventRepository;
    private UserRepository userRepository;

    public SimpleEventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event create(Event event, String email) throws NotCreatedException {
        LOGGER.info("EventService: creating event");
        try {
            User employee = this.userRepository.findByEmail(email);
            if (employee != null) {
                event.setEmployee(employee);
                return this.eventRepository.save(event);
            } else {
                throw new NotCreatedException(String.format(
                    "The event %s could not be created: only an employee can add one", event.getTitle()));
            }
        } catch (DataAccessException dae) {
            LOGGER.error("EventService: event could not be created: " + dae.getMessage());
            throw new NotCreatedException(String.format("The event %s could not be created: %s",
                event.getTitle(), dae.getMessage()));
        }
    }

    public List<Event> getAll() throws NotFoundException {
        LOGGER.info("EventService: getting all events ...");
        try {
            List<Event> events = this.eventRepository.findAll();
            if (events != null || !events.isEmpty()) {
                return events;
            } else {
                throw new NotFoundException("No events have been found.");
            }
        } catch (DataAccessException dae) {
            LOGGER.error("EventService: no event found: " + dae.getMessage());
            throw new NotFoundException(String.format("No event found: %s",
                dae.getMessage()));
        }
    }

    public List<Event> getTopEvents() throws NotFoundException {
        LOGGER.info("EventService: getting top events ...");
        try {
            List<Event> top10Events = new ArrayList<>(10);
            int count = 0;
            for (Event event : this.eventRepository.findTopEvents()) {
                if (count > 9) {
                    break;
                }
                top10Events.add(event);
                count++;
            }
            return top10Events;
        } catch (DataAccessException dae) {
            LOGGER.error("Could not fetch top ten events: {}", dae.getMessage());
            throw new NotFoundException(String.format("Could not fetch top ten events: %s", dae.getMessage()));
        }
    }

    @Override
    public List<Event> getFiltered(String searchTerm, String category,
                                   LocalDate startDate, LocalDate endDate,
                                   Double price, Double duration) throws NotFoundException {
        try {
            List<Event> events = this.eventRepository.findAllByCriteria(searchTerm, category, startDate, endDate, price, duration);
        } catch (DataAccessException dae) {
            LOGGER.error("No events found matching the criteria: {}", dae.getMessage());
            throw new NotFoundException(String.format("No events found matching the criteria: %s", dae.getMessage()));
        }
        return null;
    }
}
