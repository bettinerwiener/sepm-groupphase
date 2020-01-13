package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.CustomerNews;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface CustomerNewsService {

    /**
     * Finds all existing links between user and news entries
     * @return list of all links persisted in the database
     * @throws NotFoundException in case something goes wrong while accessing the database
     */
    List<CustomerNews> findAll() throws NotFoundException;

    List<CustomerNews> findCustomerNewsByCustomer(String email, Boolean read) throws NotFoundException;

    CustomerNews setRead(CustomerNews customerNews);
}
