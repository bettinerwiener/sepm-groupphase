package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SeatDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.SeatMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.SeatService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/seats")
@Slf4j
public class SeatEndpoint {

    private SeatMapper seatMapper;
    private SeatService seatService;

    public SeatEndpoint(SeatMapper seatMapper, SeatService seatService) {
        this.seatMapper = seatMapper;
        this.seatService = seatService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all seats", authorizations = {@Authorization(value = "apiKey")})
    public List<SeatDto> getAll() {
        return this.seatService.getAll().stream().map(seat -> this.seatMapper.seatToSeatDto(seat))
            .collect(Collectors.toList());
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create various seats", authorizations = {@Authorization(value = "apiKey")})
    public List<SeatDto> createSeats(@RequestBody List<SeatDto> seatDtoArray) {
        System.out.println("seats are here");
        System.out.println(seatDtoArray);
        SeatDto createdSeatDto;
        List<SeatDto> seatDtos = new ArrayList<>();
        for (SeatDto seatDto : seatDtoArray) {
            createdSeatDto = seatMapper.seatToSeatDto(seatService
                .create(seatMapper.seatDtoToSeat(seatDto)));
            seatDtos.add(createdSeatDto);
        }
        return seatDtos;
    }
}
