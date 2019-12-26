package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface SeatService {

    /**
     * Finds a seat by its id
     * @param id to find the seat
     * @return the seat found by id
     * @throws NotFoundException in case there is no such seat in the database
     */
    public Seat findById(Long id) throws NotFoundException;

    /**
     * Gets all seats
     * @return a list of seats currently in the database
     * @throws NotFoundException in case there are no seats in the database
     */
    public List<Seat> getAll() throws NotFoundException;

    /**
     * Finds all seats in a certain location
     * @param room for which seats shall be found
     * @return a list of seats for the requested location
     * @throws NotFoundException in case there no seats in the requested location
     */
    public List<Seat> findByRoom(Room room) throws NotFoundException;

    /**
     * Creates a seat
     * @param seat to be created
     * @return the seat created
     * @throws NotCreatedException in case something went wrong while trying to persist the entity
     */
    public Seat create(Seat seat) throws NotCreatedException;
}
