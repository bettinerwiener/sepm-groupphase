package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Artist;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface ArtistService {

    /**
     * Gets all artists
     * @return all artists currently in the database
     * @throws NotFoundException will be thrown if something goes wrong during database access
     */
    List<Artist> getAll() throws NotFoundException;

}
