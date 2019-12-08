package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface EventService {

    /**
     * Create a single event
     *
     * @param event to be created
     * @return the created event
     * @throws NotCreatedException in case something went wrong when accessing the database
     */
    Event create(Event event, String email) throws NotCreatedException;

    /**
     * Get all events in the database
     *
     * @return all events found in the database
     * @throws NotFoundException in case something went wrong when accessing the database
     */
    List<Event> getAll() throws NotFoundException;

    List<Event> getTopEvents() throws NotFoundException;
}
