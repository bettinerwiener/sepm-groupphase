package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;

import java.util.List;

public interface SectionService {

    /**
     * Finds a section by its id
     * @param id to find the section
     * @return the section found by id
     * @throws NotFoundException in case there is no such section in the database
     */
    public Section findById(Long id) throws NotFoundException;

    /**
     * Gets all sections
     * @return a list of sections currently in the database
     * @throws NotFoundException in case there are no sections in the database
     */
    public List<Section> getAll() throws NotFoundException;

    /**
     * Finds all sections in a certain location
     * @param room for which sections shall be found
     * @return a list of sections for the requested location
     * @throws NotFoundException in case there no sections in the requested location
     */
    public List<Section> findByRoom(Room room) throws NotFoundException;

    /**
     * Finds all seats of a certain section
     * @param section for which seats shall be found
     * @return a list of seats for the requested section
     * @throws NotFoundException in case there are no seats in the requested section
     */
    public List<Seat> findSeats(Section section) throws NotFoundException;

    /**
     * Creates a section
     * @param section to be created
     * @return the section created
     * @throws NotCreatedException in case something went wrong while trying to persist the entity
     */
    public Section create(Section section) throws NotCreatedException;
}
