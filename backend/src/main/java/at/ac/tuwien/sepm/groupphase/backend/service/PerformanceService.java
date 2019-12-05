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
     * @param event for which a performance shall be created
     * @param room in which the performance will take place
     * @param dateTime at which the event will be performed
     * @return the created performance
     * @throws NotCreatedException in case something goes wrong while persisting the entity
     */
    public EventPerformance create(Event event, Room room, Date dateTime) throws NotCreatedException;

    public List<EventPerformance> getAll() throws NotFoundException;

    public List<EventPerformance> findByEvent(Event event) throws NotFoundException;

    public List<EventPerformance> findByRoom(Room room) throws NotFoundException;
}
