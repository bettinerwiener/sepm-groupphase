package at.ac.tuwien.sepm.groupphase.backend.unittests;


import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.repository.LocationRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.RoomRepository;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class RoomRespositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void findRoomByIdReturnsRoom() {
        Optional<Room> room = roomRepository.findById(1L);
        assertAll(
            () -> assertEquals(1, room.get().getId())
        );
    }

    @Test
    public void tryFindingNonExistingRoomReturnsNull() {
        Optional<Room> room = roomRepository.findById(50L);
        assertAll (
            () -> assertFalse(room.isPresent())
        );
    }

    @Test
    public void createRoomForExistingLocationReturnsRoom() {
        Room room = new Room();
        room.setName("Victor Hugo");
        Optional<Location> location = locationRepository.findById(1L);
        room.setLocation(location.get());
        Room createdRoom = roomRepository.save(room);
        assertAll(
            () -> assertEquals(room.getName(), createdRoom.getName()),
            () -> assertEquals(room.getLocation().getId(), createdRoom.getLocation().getId())
        );
    }

    @Test
    public void createRoomForNonExistingLocationThrowsNotFoundException() {
        Room room = new Room();
        room.setName("Victor Hugo");
        Optional<Location> location = locationRepository.findById(50L);
        room.setLocation(location.isPresent() ? location.get() : null);
        assertThrows(DataIntegrityViolationException.class, () -> roomRepository.save(room));
        assertAll(
            () -> assertFalse(location.isPresent())
        );
    }
}
