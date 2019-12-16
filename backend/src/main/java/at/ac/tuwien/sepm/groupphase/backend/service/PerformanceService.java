package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface PerformanceService {

    /**
     * Creates a performance for an event at a certain location and time
     * @param eventPerformance to be created
     * @return the created performance
     * @throws NotCreatedException in case something goes wrong while persisting the entity
     */

    EventPerformance create(EventPerformance eventPerformance) throws NotCreatedException;

    /**
     * Gets all performances
     * @return a list with all performances found in the database
     * @throws NotFoundException in case something went wrong while accessing the database
     */
    List<EventPerformance> getAll() throws NotFoundException;

    /**
     * Gets all performances of an event
     * @param event whose performances shall be found
     * @return a list of performances of the event specified
     * @throws NotFoundException
     */
    List<EventPerformance> findByEvent(Long event) throws NotFoundException;

    /**
     * Gets all performances of the room specified
     * @param room whose performances shall be found
     * @return a list of all performances found for the room specified
     * @throws NotFoundException in case no performances could be found or something went wrong while
     *                          accessing the database
     */
    List<EventPerformance> findByRoom(Room room) throws NotFoundException;
}
