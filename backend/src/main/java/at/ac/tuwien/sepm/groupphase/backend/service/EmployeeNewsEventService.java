package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface EmployeeNewsEventService {

    /**
     * Creates a link between an event and a news entry
     * @param employeeNewsEvent for which the link shall be created
     * @return the object representing the link if successful
     * @throws NotCreatedException in case something goes wrong while accessing the database
     */
    EmployeeNewsEvent create(EmployeeNewsEvent employeeNewsEvent) throws NotCreatedException;

    /**
     * Finds all existing links between events and news entries
     * @return list of all links persisted in the database
     * @throws NotFoundException in case something goes wrong while accessing the database
     */
    List<EmployeeNewsEvent> findAll() throws NotFoundException;

    /**
     * Finds an event news entry with a certain id
     * @param id the event news entry is to be found for
     * @return a link with @param id as found in the database
     * @throws NotFoundException in case something goes wrong while accessing the database or no link is found
     */
    EmployeeNewsEvent findById(Long id) throws NotFoundException;

    /**
     * Finds an event news entry with a certain id
     * @param newsId the event news entry is to be found for
     * @return a link with @param id as found in the database
     * @throws NotFoundException in case something goes wrong while accessing the database or no link is found
     */
    List<EmployeeNewsEvent> getForNews(Long newsId) throws NotFoundException;
}
