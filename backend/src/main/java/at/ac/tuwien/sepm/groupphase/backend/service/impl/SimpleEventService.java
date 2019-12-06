package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Employee;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EmployeeRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

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
}
