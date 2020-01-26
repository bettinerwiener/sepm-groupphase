package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Artist;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.ArtistRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SimpleArtistService implements ArtistService {

    private ArtistRepository artistRepository;

    public SimpleArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAll() throws NotFoundException {
        try {
            List<Artist> artists = this.artistRepository.findAllOrderByLastName();
            return artists;
        } catch (DataAccessException dae) {
            log.error("No artists could be found: {}", dae.getMessage());
            throw new NotFoundException(String.format("No artists could be found: %s", dae.getMessage()));
        }
    }
}
