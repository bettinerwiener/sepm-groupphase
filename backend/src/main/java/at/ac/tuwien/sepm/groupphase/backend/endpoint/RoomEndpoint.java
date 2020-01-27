package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SeatDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.RoomMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.SeatMapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.SectionMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import at.ac.tuwien.sepm.groupphase.backend.service.RoomService;
import at.ac.tuwien.sepm.groupphase.backend.service.SectionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/rooms")
@Slf4j
public class RoomEndpoint {

    private RoomMapper roomMapper;
    private RoomService roomService;
    private SectionService sectionService;
    private SectionMapper sectionMapper;
    private SeatMapper seatMapper;

    public RoomEndpoint(RoomMapper roomMapper, RoomService roomService, SectionService sectionService) {
        this.roomMapper = roomMapper;
        this.roomService = roomService;
        this.sectionService = sectionService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all rooms", authorizations = {@Authorization(value = "apiKey")})
    public List<RoomDto> getAll() {
        return this.roomService.getAll().stream().map(room -> this.roomMapper.roomToRoomDto(room))
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a room", authorizations = {@Authorization(value = "apiKey")})
    public RoomDto create(@RequestBody RoomDto roomDto) {
        RoomDto createdRoomDto = roomMapper.roomToRoomDto(roomService
            .create(roomMapper.roomDtoToRoom(roomDto)));
        System.out.println(createdRoomDto);
        return createdRoomDto;
    }

    @GetMapping(value = "/seats/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get section by room ID", authorizations = {@Authorization(value = "apiKey")})
    public List<SeatDto> getSeatsByRoomId(@PathVariable Long id) {
        List<Section> sections = this.sectionService.findByRoom(this.roomService.findById(id));
        List<Seat> seats = new ArrayList<>();
        for(Section section : sections){
            seats.addAll(this.sectionService.findSeats(section));
        }
        return seats.stream().map(seat -> this.seatMapper.seatToSeatDto(seat))
            .collect(Collectors.toList());
    }

}
