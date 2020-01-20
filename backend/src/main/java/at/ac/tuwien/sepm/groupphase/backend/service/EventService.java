package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.BitSet;
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
     */
    List<Event> getAll();

    /**
     * Gets top ten events
     * @return a list with at most ten events, where the most tickets have been bought.
     */
    List<Event> getTopEvents();

    /**
     * Filters all events according to the search parameters
     * @param searchTerm a string free text element just one word is allowed
     * @param category s string representing the category of the event
     * @param startDate the date which must lie before today
     * @param endDate the date which must llie after today
     * @param price the maximum price the customer wants to pay
     * @param duration the maximum duration of the event
     * @param location where the performance should take place
     * @param artist who is responsible for the event or created the event
     * @return a list of events meeting all the search criteria
     */
    List<Event> getFiltered(String searchTerm, String category,
                            LocalDateTime startDate, LocalDateTime endDate,
                            Double price, Double duration,
                            String location, Long artist);

    /**
     * Gets an event with the specified id
     * @param id of the event looked for
     * @return the event with the @param id
     * @throws NotFoundException in case no event with the id could be found or
     *                           something wnet wrong while accessing the database
     */
    Event getById(Long id) throws NotFoundException;
}
