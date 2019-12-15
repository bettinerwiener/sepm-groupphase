package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.TicketTestData;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.OrderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void FindTicketThenUpdateTicketAndSaveTicketThenFindSameTicket() {



        Ticket ticket= ticketRepository.findFirstById(1L);
        ticket.setStatus(Ticket.Status.BOUGHT);
        ticketRepository.save(ticket);
        assertAll(
            () -> assertEquals(7, ticketRepository.findAll().size()),
            () -> assertNotNull(ticketRepository.findById(ticket.getId())),
            () -> assertEquals(Ticket.Status.BOUGHT, ticketRepository.findFirstById(1L).getStatus())
        );
    }







}
