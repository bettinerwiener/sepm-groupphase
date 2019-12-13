package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface OrderService {

    /**
     * Create a single event
     * @return the created event
     * @throws NotCreatedException in case something went wrong when accessing the database
     */

    List<Order> getAll() throws NotFoundException;
    List<Order> findByUserId(Long id) throws NotFoundException;


}
