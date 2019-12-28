package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.SeatRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.SectionRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.SectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SimpleSectionService implements SectionService {

    private SectionRepository sectionRepository;
    private SeatRepository seatRepository;

    public SimpleSectionService(SectionRepository sectionRepository, SeatRepository seatRepository) {
        this.sectionRepository = sectionRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Section findById(Long id) throws NotFoundException {
        try {
            Optional<Section> section = this.sectionRepository.findById(id);
            if (section.isPresent()) {
                return section.get();
            } else {
                log.error("A section with id %d could not be found.", id);
                throw new NotFoundException(String.format("A section with id %d could not be found", id));
            }
        } catch (DataAccessException dae) {
            log.error("A section with id %d could not be found: %s", id, dae.getMessage());
            throw new NotFoundException(String.format("A section with id %d could not be found: %s", id, dae.getMessage()));
        }
    }

    @Override
    public List<Section> getAll() throws NotFoundException {
        try {
            List<Section> sections = this.sectionRepository.findAll();
            if (sections != null && !sections.isEmpty()) {
                return sections;
            }
            else {
                log.error("There are no sections in the database.");
                throw new NotFoundException("No sections have been found.");
            }
        } catch (DataAccessException dae) {
            log.error("No sections have been found: %s", dae.getMessage());
            throw new NotFoundException(String.format("No sections have been found: %s", dae.getMessage()));
        }
    }



    @Override
    public List<Section> findByRoom(Room room) throws NotFoundException {
        try {
            List<Section> sections = this.sectionRepository.findByRoom(room);
            if (sections != null && !sections.isEmpty()) {
                return sections;
            } else {
                log.error("No sections for the requested room %d could be found",
                    room.getName());
                throw new NotFoundException(String.format("No sections for the requested room %d could be found",
                    room.getName()));
            }
        } catch (DataAccessException dae) {
            log.error("No sections for the requested room %d could be found: %s",
                room.getName(), dae.getMessage());
            throw new NotFoundException(String.format("No sections for the requested room %d could be found: %s",
                room.getName(), dae.getMessage()));
        }
    }

    @Override
    public List<Seat> findSeats(Section section) throws NotFoundException {
        try {
            List<Seat> seats = this.seatRepository.findBySection(section);
            if (seats != null && !seats.isEmpty()) {
                return seats;
            }
            else {
                log.error(String.format("There are no seats for section %d in the database.", section.getId()));
                throw new NotFoundException(String.format("No seats have been found for section %d", section.getId()));
            }
        } catch (DataAccessException dae) {
            log.error(String.format("No seats have been found for section %d: %s", section.getId(), dae.getMessage()));
            throw new NotFoundException(String.format("No seats have been found for section %d: %s", section.getId(), dae.getMessage()));
        }
    }

    @Override
    public Section create(Section section) throws NotCreatedException {
        try {
            Section createdSection = sectionRepository.saveAndFlush(section);
            return createdSection;
        } catch (DataAccessException dae) {
            log.error("Section could not be created: %s", dae.getMessage());
            throw new NotCreatedException(String.format("Section could not be created: %s", dae.getMessage()));
        }
    }
}
