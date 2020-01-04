package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface CustomerNewsService {
    /**
     * Creates a link between an event and a news entry
     * @param customerNews for which the link shall be created
     * @return the object representing the link if successful
     * @throws NotCreatedException in case something goes wrong while accessing the database
     */
    CustomerNews create(CustomerNews customerNews) throws NotCreatedException;

    /**
     * Finds all existing links between events and news entries
     * @return list of all links persisted in the database
     * @throws NotFoundException in case something goes wrong while accessing the database
     */
    List<CustomerNews> findAll() throws NotFoundException;
}
