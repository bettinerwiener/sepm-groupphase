package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.NewsDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.News;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/events")
public class EventEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventEndpoint(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    @ApiOperation(value = "Get all events", authorizations = {@Authorization(value = "apiKey")})
    public List<EventDto> getAll(@RequestParam(required = false) String searchTerm,
                                 @RequestParam(required = false) String category,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
                                 @RequestParam(required = false) Double price,
                                 @RequestParam(required = false) Double duration,
                                 @RequestParam(required = false) String location,
                                 @RequestParam(required = false) String artist) {
        List<EventDto> eventDtos;
        if (searchTerm == null && category == null
            && startDate == null && endDate == null
            && price == null && duration == null
            && location == null && artist == null) {
            eventDtos = eventService.getAll().stream().
                map(event -> eventMapper.eventToEventDto(event)).collect(Collectors.toList());

        } else {
            List<Event> events = eventService.getFiltered(searchTerm, category,
                startDate, endDate, price, duration, location, artist);
            LOGGER.info("number of events: {}", events == null ? 0 : events.size());
            eventDtos = events.stream()
                .map(event -> eventMapper.eventToEventDto(event)).collect(Collectors.toList());
        }
        return eventDtos;
    }

    @CrossOrigin
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    @ApiOperation(value = "Create a new event", authorizations = {@Authorization(value = "apiKey")})
    public EventDto create(@RequestBody EventDto eventDto, @AuthenticationPrincipal String username) {
        return eventMapper.eventToEventDto(
            eventService.create(eventMapper.eventDtoToEvent(eventDto), username));
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/topten")
    @ApiOperation(value = "Get top ten events", authorizations = {@Authorization(value = "apiKey")})
    public List<EventDto> getTopTenEvents() {
        List<EventDto> eventDtos = eventService.getTopEvents().stream().
            map(event -> eventMapper.eventToEventDto(event)).collect(Collectors.toList());
        return eventDtos;
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @ApiOperation(value = "Get event by id", authorizations = {@Authorization(value = "apiKey")})
    public EventDto getById(@PathVariable("id") Long id) {
        return this.eventMapper.eventToEventDto(this.eventService.getById(id));
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add an image to a news entry", authorizations = {@Authorization(value = "apiKey")})
    public EventDto update(@RequestParam("image") MultipartFile image, @PathVariable("id") Long id)  {
        return this.eventMapper.eventToEventDto(this.eventService.updateWithImage(id, image));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Add an image to a news entry", authorizations = {@Authorization(value = "apiKey")})
    public EventDto updateImage(@RequestParam("image") MultipartFile image, @PathVariable("id") Long id)  {
        return this.eventMapper.eventToEventDto(this.eventService.updateWithImage(id, image));
    }

    @GetMapping(
        value = "/{id}/image",
        produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value= "Get the image for a news entry", authorizations = {@Authorization(value = "apiKey")})
    public @ResponseBody byte[] getImageForNews(@PathVariable("id") Long id) {
        Event event = this.eventService.getById(id);
        return event.getImage();
    }

}
