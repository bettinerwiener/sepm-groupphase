package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.RoomDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import org.mapstruct.Named;

public interface RoomMapper {

    @Named("room")
    RoomDto eventToEventDto(Room room);

    Room eventDtoToEvent(RoomDto roomDto);
}
