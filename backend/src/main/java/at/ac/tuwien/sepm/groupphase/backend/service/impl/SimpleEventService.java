package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepositoryCustom;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import org.apache.pdfbox.io.IOUtils;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.ArrayList;
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

            User employee = this.userRepository.findFirstByEmail(email);
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

    public List<Event> getAll() {
        LOGGER.info("EventService: getting all events ...");
        try {
            List<Event> events = this.eventRepository.findAll();
            if (events != null || !events.isEmpty()) {
                return events;
            } else {
                return new ArrayList<>();
            }
        } catch (DataAccessException dae) {
            LOGGER.error("EventService: no event found: " + dae.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Event> getTopEvents() {
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
            return new ArrayList<>();
        }
    }

    @Override
    public List<Event> getFiltered(String searchTerm, String category,
                                   LocalDate startDate, LocalDate endDate,
                                   Double price, Double duration,
                                   Long location, Long artist) {
        try {
            List<Event> events = this.eventRepository.findAllByCriteria(searchTerm,
                category, startDate, endDate, price, duration, location, artist);
            if (events != null && !events.isEmpty()) {
                return events;
            }
            else {
                return new ArrayList<>();
            }
        } catch (DataAccessException dae) {
            LOGGER.error("No events found matching the criteria: {}", dae.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Event getById(Long id) throws NotFoundException {
        try {
            Optional<Event> event = this.eventRepository.findById(id);
            if (event.isPresent()) {
                return event.get();
            } else {
                throw new NotFoundException(String.format("Event with id %d could not be found", id));
            }
        } catch (DataAccessException dae) {
            throw new NotFoundException(String.format("Event with id %d could not be found:%s ", id,
                dae.getMessage()));
        }
    }

    @Override
    public Event updateWithImage(Long id, MultipartFile image) throws NotFoundException {
        LOGGER.info("Updating news entry {} with image ...", id);
        try {
            Optional<Event> result= this.eventRepository.findById(id);
            if (!result.isPresent()) {
                LOGGER.info("The news entry with id {} could not be found.", id);
                throw new NotFoundException(String.format("The news entry with id %d could not be found.", id));
            }
            Event toUpdate = result.get();
            toUpdate.setImage(IOUtils.toByteArray(image.getInputStream()));
            this.eventRepository.saveAndFlush(toUpdate);
        } catch (IOException | DataAccessException dae) {
            LOGGER.error("The news with id {} could not be updated: {}", id, dae.getMessage());
            throw new NotFoundException(String.format("The news with id %d could not be updated: %s",
                id, dae.getMessage()));
        }
        return null;
    }
}
