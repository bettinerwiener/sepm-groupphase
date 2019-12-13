package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/tickets")
public class TicketEndpoint {

    private TicketService ticketService;
    private TicketMapper ticketMapper;

    public TicketEndpoint(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketDto> getTicketsByPerformance(@RequestParam() Long performance) {
        List<TicketDto> ticketDtos = this.ticketService.findByPerformanceId(performance).stream().
            map(ticket -> ticketMapper.ticketToTicketDto(ticket)).collect(Collectors.toList());
        return ticketDtos;
    }
}
