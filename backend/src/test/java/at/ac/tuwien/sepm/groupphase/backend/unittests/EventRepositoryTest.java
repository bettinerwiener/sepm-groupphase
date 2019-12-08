package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.EventTestData;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class EventRepositoryTest implements EventTestData {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void findEventByIdReturnsEvent() {
        Optional<Event> event = eventRepository.findById(1L);
        assertAll(
            () -> assertEquals(1, event.get().getId())
        );
    }

    @Test
    public void findEventByNonExistingIdReturnsNull() {
        Optional<Event> event = eventRepository.findById(50L);
        assertAll(
            () -> assertFalse(event.isPresent())
        );
    }

    @Test
    public void createEventReturnsCreatedEvent() {
        Event event = new Event();
        event.setTitle("Les Misérables");
        event.setShortDescription("kenn ich nicht");
        event.setContents("une histoire cruelle");
        event.setCategory(Event.Category.FILM);
        event.setDuration(4.7);

        User user = userRepository.findByEmail(ADMIN_USER);
        Event createdEvent = eventRepository.save(event);
        assertAll(
            () -> assertEquals(event.getTitle(), createdEvent.getTitle())
        );
    }

    @Test
    public void findTopEventsReturnsEventWithId1AtTop() {
        List<Ticket> tickets = this.ticketRepository.findAll();
        List<Event> events = this.eventRepository.findTopEvents();
        assertAll(
            () -> assertEquals(1L, events.get(0).getId())
        );
    }

}
