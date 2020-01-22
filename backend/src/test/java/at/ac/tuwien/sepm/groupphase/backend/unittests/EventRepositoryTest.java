package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.EventTestData;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepositoryCustom;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

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

    @Autowired
    PlatformTransactionManager txm;


    TransactionStatus txstatus;

    @BeforeEach
    public void setupDBTransaction() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        txstatus = txm.getTransaction(def);
        assumeTrue(txstatus.isNewTransaction());
        txstatus.setRollbackOnly();
    }

    @AfterEach
    public void tearDownDBData() {
        txm.rollback(txstatus);
    }

    @Test
    public void findEventByIdReturnsEvent() {
        Optional<Event> event = eventRepository.findById(1L);
        assertAll(
            () -> assertEquals(1, event.get().getId())
        );
    }

    @Test
    public void findEventByNonExistingIdReturnsNull() {
        Optional<Event> event = eventRepository.findById(400L);
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

        User user = userRepository.findFirstByEmail(ADMIN_USER);
        event.setEmployee(user);
        Event createdEvent = eventRepository.save(event);
        assertAll(
            () -> assertEquals(event.getTitle(), createdEvent.getTitle())
        );
    }

    @Test
    public void findTopEventsReturnsEventWithId1AtTop() {
        List<Event> events = this.eventRepository.findTopEvents();
        assertAll(
            () -> assertEquals(2L, events.get(0).getId())
        );
    }

}
