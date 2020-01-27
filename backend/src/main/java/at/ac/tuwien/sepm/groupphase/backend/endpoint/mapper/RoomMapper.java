package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface RoomMapper {

    @Named("room")
    RoomDto roomToRoomDto(Room room);

    Room roomDtoToRoom(RoomDto roomDto);
}
