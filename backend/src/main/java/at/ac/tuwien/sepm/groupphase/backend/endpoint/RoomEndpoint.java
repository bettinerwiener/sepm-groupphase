package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.RoomMapper;
import at.ac.tuwien.sepm.groupphase.backend.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/rooms")
@Slf4j
public class RoomEndpoint {

    private RoomMapper roomMapper;
    private RoomService roomService;

    public RoomEndpoint(RoomMapper roomMapper, RoomService roomService) {
        this.roomMapper = roomMapper;
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDto> getAll() {
        return this.roomService.getAll().stream().map(room -> this.roomMapper.roomToRoomDto(room)).collect(Collectors.toList());
    }
}
