package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.PerformanceDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.PerformanceMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.RoomMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.PerformanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/performances")
@Slf4j
public class PerformanceEndpoint {

    private PerformanceMapper performanceMapper;
    private PerformanceService performanceService;
    private RoomMapper roomMapper;
    private EventMapper eventMapper;

    public PerformanceEndpoint(PerformanceMapper performanceMapper, PerformanceService performanceService,
                               RoomMapper roomMapper, EventMapper eventMapper) {
        this.performanceMapper = performanceMapper;
        this.performanceService = performanceService;
        this.eventMapper = eventMapper;
        this.roomMapper = roomMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerformanceDto create(@RequestBody EventDto eventDto, @RequestBody RoomDto roomDto, @RequestBody LocalDateTime date) {
        return this.performanceMapper.performanceToPerformanceDto(this.performanceService.create(this.eventMapper.eventDtoToEvent(eventDto),
            this.roomMapper.roomDtoToRoom(roomDto), date));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PerformanceDto> getAll() {
        log.info("Getting all performances ...");
        List<PerformanceDto> performanceDtos = this.performanceService.getAll().stream()
            .map(performance -> this.performanceMapper.performanceToPerformanceDto(performance)).collect(Collectors.toList());
        log.info("Got all performances; the first one is: %s", performanceDtos.get(0).toString());
        return performanceDtos;
    }
}
