package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.PerformanceDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.TicketMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/tickets")
@Slf4j
public class TicketEndpoint {

    private TicketMapper ticketMapper;
    private TicketService ticketService;

    public TicketEndpoint(TicketMapper ticketMapper, TicketService ticketService) {
        this.ticketMapper = ticketMapper;
        this.ticketService = ticketService;
    }

    @CrossOrigin
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all tickets", authorizations = {@Authorization(value = "apiKey")})
    public List<TicketDto> getAll(@RequestParam(required = false) Long performance) {
        log.info("Getting all performances ...");
        if (performance != null) {
            List<TicketDto> ticketDtos = this.ticketService.findByPerformance(performance).stream().
                map(ticket -> ticketMapper.ticketToTicketDto(ticket)).collect(Collectors.toList());
            return ticketDtos;
        } else {
            List<TicketDto> ticketDtos = this.ticketService.getAll().stream()
                .map(ticket -> this.ticketMapper.ticketToTicketDto(ticket)).collect(Collectors.toList());
            log.info("Got all performances; the first one is: {}", ticketDtos.get(0).toString());
            return ticketDtos;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update a ticket", authorizations = {@Authorization(value = "apiKey")})
    public TicketDto update(@RequestBody TicketDto ticketDto) {
        return this.ticketMapper.ticketToTicketDto(
            this.ticketService.update(this.ticketMapper.ticketDtoToTicket(ticketDto)));
    }
}
