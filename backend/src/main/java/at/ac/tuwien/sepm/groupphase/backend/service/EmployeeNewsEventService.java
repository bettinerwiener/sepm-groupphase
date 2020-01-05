package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface EmployeeNewsEventService {

    /**
     * Finds all existing links between events and news entries
     * @return list of all links persisted in the database
     * @throws NotFoundException in case something goes wrong while accessing the database
     */
    List<EmployeeNewsEvent> findAll() throws NotFoundException;
}
