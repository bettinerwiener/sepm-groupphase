package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EmployeeNewsEventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EmployeeNewsEventMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployeeNewsEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/eventnews")
public class EmployeeNewsEventEndpoint {

    private EmployeeNewsEventMapper employeeNewsEventMapper;
    private EmployeeNewsEventService employeeNewsEventService;

    public EmployeeNewsEventEndpoint(EmployeeNewsEventMapper employeeNewsEventMapper,
                                     EmployeeNewsEventService employeeNewsEventService) {
        this.employeeNewsEventMapper = employeeNewsEventMapper;
        this.employeeNewsEventService = employeeNewsEventService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Find all news entries for all events", authorizations = {@Authorization(value = "apiKey")})
    public List<EmployeeNewsEventDto> getAll() {
        return this.employeeNewsEventMapper.eNEListToENEDtoList(this.employeeNewsEventService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a news entry for an event", authorizations = {@Authorization(value = "apiKey")})
    public EmployeeNewsEventDto create(@RequestBody EmployeeNewsEventDto employeeNewsEventDto) {
        return this.employeeNewsEventMapper.empNewsEventToEmpNewsEventDto(
            this.employeeNewsEventService.create(this.employeeNewsEventMapper.
                empNewsEventDtoToEmpNewsEvent(employeeNewsEventDto)));
    }
}
