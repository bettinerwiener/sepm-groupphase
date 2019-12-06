package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.repository.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void findingLocationByIdReturnsLocation() {
        Optional<Location> locationOptional = locationRepository.findById(1L);
        assertAll(
            () -> assertEquals(1, locationOptional.get().getId())
        );
    }

    @Test
    public void findingLocationByNonExistingIdReturnsNull() {
        Optional<Location> locationOptional = locationRepository.findById(50L);
        assertAll(
            () -> assertFalse(locationOptional.isPresent())
        );
    }

    @Test
    public void creatingLocationReturnsLocation() {
        Location location = new Location();
        location.setName("Erwin Schrödinger");
        location.setCity("Linz");
        location.setStreet("Landstrasse 13");
        location.setPostalCode(4020L);

        Location createdLocation = locationRepository.save(location);
        assertAll(
            () -> assertEquals(location.getName(), createdLocation.getName()),
            () -> assertEquals(location.getCity(), createdLocation.getCity())
        );
    }
}
