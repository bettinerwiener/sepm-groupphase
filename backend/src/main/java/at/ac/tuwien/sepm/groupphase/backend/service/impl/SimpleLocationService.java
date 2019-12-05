package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.LocationRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SimpleLocationService implements LocationService {

    private LocationRepository locationRepository;

    public SimpleLocationService() {};
    public SimpleLocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public Location findById(Long id) throws NotFoundException {
        try {
            Optional<Location> location = this.locationRepository.findById(id);
            if (location.isPresent()) {
                return location.get();
            } else {
                log.error("A location with id %d could not be found.", id);
                throw new NotFoundException(String.format("A location with id %d could not be found", id));
            }
        } catch (DataAccessException dae) {
            log.error("A location with id %d could not be found: %s", id, dae.getMessage());
            throw new NotFoundException(String.format("A location with id %d could not be found: %s", id, dae.getMessage()));
        }
    }

    @Override
    public List<Location> getAll() throws NotFoundException {
        try {
            List<Location> locations = this.locationRepository.findAll();
            if (locations != null && !locations.isEmpty()) {
                return locations;
            }
            else {
                log.error("There are no locations in the database.");
                throw new NotFoundException("No locations have been found.");
            }
        } catch (DataAccessException dae) {
            log.error("No locations have been found: %s", dae.getMessage());
            throw new NotFoundException(String.format("No locations have been found: %s", dae.getMessage()));
        }
    }
}
