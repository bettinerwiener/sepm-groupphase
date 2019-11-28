package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;

public interface EventService {

    /**
     * Create a single event
     *
     * @param event to be created
     * @return the created event
     * @throws NotCreatedException in case something went wrong when accessing the database
     */
    Event create(Event event) throws NotCreatedException;
}
