package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.TicketTestData;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
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


import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@ExtendWith(SpringExtension.class)
// This test slice annotation is used instead of @SpringBootTest to load only repository beans instead of
// the entire application context
@DataJpaTest
@ActiveProfiles("test")
public class TicketRepositoryTest implements TicketTestData {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrderRepository orderRepository;

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
    public void FindTicketThenUpdateTicketAndSaveTicketThenFindSameTicket() {



        Ticket ticket= ticketRepository.findFirstById(13L);
        ticket.setStatus(Ticket.Status.BOUGHT);
        ticketRepository.save(ticket);
        assertAll(
            () -> assertEquals(21, ticketRepository.findAll().size()),
            () -> assertNotNull(ticketRepository.findById(ticket.getId())),
            () -> assertEquals(Ticket.Status.BOUGHT, ticketRepository.findFirstById(13L).getStatus())
        );
    }

    @Test
    public void FindUserIdWhoReservedTicket() {

        Ticket ticket= ticketRepository.findFirstById(14L);
        Long userId = ticketRepository.findUserIdToTicket(ticket.getId());

        assertAll(
            () -> assertNotNull(ticketRepository.findById(ticket.getId())),
            () -> assertEquals(userId,1L)
        );
    }

    @Test
    public void getReservationsWhereTimeToEventLessThanThirtyMin() {

        LocalDateTime currentTime= LocalDateTime.of(2019, Month.DECEMBER, 17, 15, 29).plus(30, ChronoUnit.MINUTES);
        List<Ticket> tickets= ticketRepository.getAllTicketsWhereReservationRunsOut(currentTime);

        assertAll(
            () -> assertEquals(tickets.size(),0)
        );
    }

}