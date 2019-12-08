package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface LocationService {

    /**
     * Gets a location by its id
     * @return the location whose id has been searched for
     * @throws NotFoundException in case the id does not exist in the location table
     */
    public Location findById(Long Id) throws NotFoundException;

    /**
     * Gets all locations in the database
     * @return a list of locations currently in the database
     * @throws NotFoundException in case no locations have been found
     */
    public List<Location> getAll() throws NotFoundException;

    public Location create(Location location) throws NotCreatedException;
}
