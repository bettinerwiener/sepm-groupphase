package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.LocationDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.LocationMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.LocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/locations")
@Slf4j
public class LocationEndpoint {

    private LocationService locationService;
    private LocationMapper locationMapper;

    public LocationEndpoint(LocationService locationService, LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
        this.locationService = locationService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @CrossOrigin("*")
    @ApiOperation(value = "Get all locations", authorizations = {@Authorization(value = "apiKey")})
    public List<LocationDto> getAll() {
        List<LocationDto> locationDtos = locationService.getAll()
            .stream().map(location -> locationMapper.locationToLocationDto(location)).collect(Collectors.toList());
        return locationDtos;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @ApiOperation(value = "Create a new location", authorizations = {@Authorization(value = "apiKey")})
    public LocationDto create(@RequestBody LocationDto locationDto) {
        LocationDto resultLocation = locationMapper.locationToLocationDto(locationService
            .create(locationMapper.locationDtoToLocation(locationDto)));
        return resultLocation;
    }
}
