package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface OrderService {

    /**
     * Gets all orders
     * @return a list of all orders currently in the database
     * @throws NotFoundException in case no orders are found
     */
    List<Order> getAll() throws NotFoundException;

    /**
     * Get a user's orders
     * @param id of the user whose orders shall be retrieved
     * @return a list of the user's orders
     * @throws NotFoundException in case no orders have been found
     */
    List<Order> findByUserId(Long id) throws NotFoundException;

    Order findById(Long id) throws NotFoundException;


}
