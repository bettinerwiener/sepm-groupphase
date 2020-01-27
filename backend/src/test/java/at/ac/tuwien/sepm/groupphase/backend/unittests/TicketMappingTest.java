package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.basetest.OrderTestData;

import at.ac.tuwien.sepm.groupphase.backend.basetest.TicketTestData;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.UserDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;

import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import org.apache.logging.log4j.util.Timer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TicketMappingTest implements TicketTestData {

    Ticket ticket = new Ticket();

    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void mapTicketToTicketDto_thenDtoHasAllProperties() {

        Order CUSTOMER_ORDER = new Order();
        CUSTOMER_ORDER.setUserId(3L);
        CUSTOMER_ORDER.setId(1L);
        ticket.setCustomerOrder(CUSTOMER_ORDER);
        ticket.setStatus(Ticket.Status.BOUGHT);
        ticket.setId(ID);
        ticket.setPerformance(EVENT_PERFORMANCE);
        ticket.setPrice(PRICE);
        ticket.setSeat(SEAT);

        TicketDto ticketDto = ticketMapper.ticketToTicketDto(ticket);

        assertAll(
            () -> assertEquals(ID, ticketDto.getId()),
            () -> assertEquals(CUSTOMER_ORDER, ticketDto.getCustomerOrder()),
            () -> assertTrue(ticketDto.getStatus().equals(Ticket.Status.BOUGHT)),
            () -> assertEquals(EVENT_PERFORMANCE, ticketDto.getPerformance()),
            () -> assertEquals(PRICE, ticketDto.getPrice()),
            () -> assertEquals(SEAT, ticketDto.getSeat())
        );
    }

    @Test
    public void mapTicketDtoToEntity_thenEntityHasAllProperties() {

        TicketDto ticketDto = new TicketDto();

        Order CUSTOMER_ORDER = new Order();
        CUSTOMER_ORDER.setUserId(3L);
        CUSTOMER_ORDER.setId(1L);
        ticketDto.setCustomerOrder(CUSTOMER_ORDER);
        ticketDto.setStatus(Ticket.Status.BOUGHT);
        ticketDto.setId(ID);
        ticketDto.setPerformance(EVENT_PERFORMANCE);
        ticketDto.setPrice(PRICE);
        ticketDto.setSeat(SEAT);

        Ticket ticket = ticketMapper.ticketDtoToTicket(ticketDto);

        assertAll(
            () -> assertEquals(ID, ticket.getId()),
            () -> assertEquals(CUSTOMER_ORDER, ticket.getCustomerOrder()),
            () -> assertTrue(ticketDto.getStatus().equals(Ticket.Status.BOUGHT)),
            () -> assertEquals(EVENT_PERFORMANCE, ticket.getPerformance()),
            () -> assertEquals(PRICE, ticket.getPrice()),
            () -> assertEquals(SEAT, ticket.getSeat())
        );
    }

}
