package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.PerformanceDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.PerformanceMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.RoomMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.PerformanceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/performances")
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

    @CrossOrigin(origins="*")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new event", authorizations = {@Authorization(value = "apiKey")})
    public PerformanceDto create(@RequestBody PerformanceDto performanceDto) {
        return this.performanceMapper.performanceToPerformanceDto(
            this.performanceService.create(this.performanceMapper.performanceDtoToPerformance(performanceDto)));
    }

    @CrossOrigin(origins="*")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all performances", authorizations = {@Authorization(value = "apiKey")})
    public List<PerformanceDto> getAll(@RequestParam(required = false) Long event) {
        log.info("Getting all performances ...");
        if (event != null) {
            List<PerformanceDto> performanceDtos = this.performanceService.findByEvent(event).stream().
                map(performance -> performanceMapper.performanceToPerformanceDto(performance)).collect(Collectors.toList());
            return performanceDtos;
        } else {
            List<PerformanceDto> performanceDtos = this.performanceService.getAll().stream()
                .map(performance -> this.performanceMapper.performanceToPerformanceDto(performance)).collect(Collectors.toList());
            return performanceDtos;
        }
    }
}
