package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.RoomRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SimpleRoomService implements RoomService {

    private RoomRepository roomRepository;

    public SimpleRoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findById(Long id) throws NotFoundException {
        try {
            Optional<Room> room = this.roomRepository.findById(id);
            if (room.isPresent()) {
                return room.get();
            } else {
                log.error("A room with id %d could not be found.", id);
                throw new NotFoundException(String.format("A room with id %d could not be found", id));
            }
        } catch (DataAccessException dae) {
            log.error("A room with id %d could not be found: %s", id, dae.getMessage());
            throw new NotFoundException(String.format("A room with id %d could not be found: %s", id, dae.getMessage()));
        }
    }

    @Override
    public List<Room> getAll() throws NotFoundException {
        try {
            List<Room> rooms = this.roomRepository.findAllOrderByName();
            if (rooms != null && !rooms.isEmpty()) {
                return rooms;
            }
            else {
                log.error("There are no rooms in the database.");
                throw new NotFoundException("No rooms have been found.");
            }
        } catch (DataAccessException dae) {
            log.error("No rooms have been found: %s", dae.getMessage());
            throw new NotFoundException(String.format("No rooms have been found: %s", dae.getMessage()));
        }
    }

    @Override
    public List<Room> findByLocation(Location location) throws NotFoundException {
        try {
            List<Room> rooms = this.roomRepository.findByLocation(location);
            if (rooms != null && !rooms.isEmpty()) {
                return rooms;
            } else {
                log.error("No rooms for the requested location %d could be found",
                    location.getName());
                throw new NotFoundException(String.format("No rooms for the requested location %d could be found",
                    location.getName()));
            }
        } catch (DataAccessException dae) {
            log.error("No rooms for the requested location %d could be found: %s",
                location.getName(), dae.getMessage());
            throw new NotFoundException(String.format("No rooms for the requested location %d could be found: %s",
                location.getName(), dae.getMessage()));
        }
    }

    @Override
    public Room create(Room room) throws NotCreatedException {
        try {
            Room createdRoom = roomRepository.saveAndFlush(room);
            System.out.println(room);
            return createdRoom;
        } catch (DataAccessException dae) {
            log.error("Room could not be created: %s", dae.getMessage());
            throw new NotCreatedException(String.format("Room could not be created: %s", dae.getMessage()));
        }
    }
}
