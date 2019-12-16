package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface RoomService {

    /**
     * Finds a room by its id
     * @param id to find the room
     * @return the room found by id
     * @throws NotFoundException in case there is no such room in the database
     */
    public Room findById(Long id) throws NotFoundException;

    /**
     * Gets all rooms
     * @return a list of rooms currently in the database
     * @throws NotFoundException in case there are no rooms in the database
     */
    public List<Room> getAll() throws NotFoundException;

    /**
     * Finds all rooms in a certain location
     * @param location for which rooms shall be found
     * @return a list of rooms for the requested location
     * @throws NotFoundException in case there no rooms in the requested location
     */
    public List<Room> findByLocation(Location location) throws NotFoundException;

    /**
     * Creates a room
     * @param room to be created
     * @return the room created
     * @throws NotCreatedException in case someting went wrong while trying to persist the entity
     */
    public Room create(Room room) throws NotCreatedException;
}
