package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class SimpleEventService implements EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private EventRepository eventRepository;

    public SimpleEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event create(Event event) throws NotCreatedException {
        LOGGER.info("EventService: creating event");
        try {
            return this.eventRepository.save(event);
        } catch (DataAccessException dae) {
            LOGGER.error("EventService: event could not be created: " + dae.getMessage());
            throw new NotCreatedException(String.format("The event %s could not be created: %s",
                event.getTitle(), dae.getMessage()));
        }
    }
}
