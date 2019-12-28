package at.ac.tuwien.sepm.groupphase.backend.service.impl;

import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotCreatedException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.SeatRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SimpleSeatService implements SeatService {

    private SeatRepository seatRepository;

    public SimpleSeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public Seat findById(Long id) throws NotFoundException {
        try {
            Optional<Seat> seat = this.seatRepository.findById(id);
            if (seat.isPresent()) {
                return seat.get();
            } else {
                log.error("A seat with id %d could not be found.", id);
                throw new NotFoundException(String.format("A seat with id %d could not be found", id));
            }
        } catch (DataAccessException dae) {
            log.error("A seat with id %d could not be found: %s", id, dae.getMessage());
            throw new NotFoundException(String.format("A seat with id %d could not be found: %s", id, dae.getMessage()));
        }
    }

    @Override
    public List<Seat> getAll() throws NotFoundException {
        try {
            List<Seat> seats = this.seatRepository.findAll();
            if (seats != null && !seats.isEmpty()) {
                return seats;
            }
            else {
                log.error("There are no seats in the database.");
                throw new NotFoundException("No seats have been found.");
            }
        } catch (DataAccessException dae) {
            log.error("No seats have been found: %s", dae.getMessage());
            throw new NotFoundException(String.format("No seats have been found: %s", dae.getMessage()));
        }
    }



    @Override
    public List<Seat> findBySection(Section section) throws NotFoundException {
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
    public Seat create(Seat seat) throws NotCreatedException {
        try {
            Seat createdSeat = seatRepository.saveAndFlush(seat);
            return createdSeat;
        } catch (DataAccessException dae) {
            log.error("Seat could not be created: %s", dae.getMessage());
            throw new NotCreatedException(String.format("Seat could not be created: %s", dae.getMessage()));
        }
    }
}
