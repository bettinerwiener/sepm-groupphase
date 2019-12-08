package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import at.ac.tuwien.sepm.groupphase.backend.service.TicketService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final TicketService ticketService;

    public EventEndpoint(EventService eventService, EventMapper eventMapper, TicketService ticketService) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.ticketService = ticketService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/events")
    @ApiOperation(value = "Get all events", authorizations = {@Authorization(value = "apiKey")})
    public List<EventDto> getAll() {
        List<EventDto> eventDtos = eventService.getAll().stream().
            map(event -> eventMapper.eventToEventDto(event)).collect(Collectors.toList());
        return eventDtos;
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/events")
    @ApiOperation(value = "Create a new event", authorizations = {@Authorization(value = "apiKey")})
    public EventDto create(@RequestBody EventDto eventDto, @AuthenticationPrincipal String username) {
        return eventMapper.eventToEventDto(eventService.create(eventMapper.eventDtoToEvent(eventDto), username));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/events/topten")
    @ApiOperation(value = "Get top ten events", authorizations = {@Authorization(value = "apiKey")})
    public List<EventDto> getTopTenEvents() {
        List<EventDto> eventDtos = eventService.getTopEvents().stream().
            map(event -> eventMapper.eventToEventDto(event)).collect(Collectors.toList());
        return eventDtos;
    }

}
