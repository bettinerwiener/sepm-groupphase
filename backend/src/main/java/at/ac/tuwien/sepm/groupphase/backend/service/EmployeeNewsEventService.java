package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;

public interface EmployeeNewsEventService {

    /**
     * Creates a link between an event and a news entry
     * @param employeeNewsEvent for which the link shall be created
     * @return the object representing the link if successful
     * @throws NotCreatedException in case something goes wrong while accessing the database
     */
    EmployeeNewsEvent create(EmployeeNewsEvent employeeNewsEvent) throws NotCreatedException;
}
