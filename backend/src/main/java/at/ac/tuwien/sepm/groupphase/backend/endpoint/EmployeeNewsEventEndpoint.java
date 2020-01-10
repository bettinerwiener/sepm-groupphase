package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EmployeeNewsEventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EmployeeNewsEventMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import at.ac.tuwien.sepm.groupphase.backend.service.EmployeeNewsEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/eventnews")
@Slf4j
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
        List<EmployeeNewsEvent> resultList = this.employeeNewsEventService.findAll();
        log.info("The resultList has {} elements.", resultList.size());
        return this.employeeNewsEventMapper.eNEListToENEDtoList(resultList);
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
