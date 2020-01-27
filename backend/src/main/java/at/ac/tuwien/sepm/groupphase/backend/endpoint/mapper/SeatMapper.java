package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SeatDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface SeatMapper {

    @Named("seat")
    SeatDto seatToSeatDto(Seat seat);

    Seat seatDtoToSeat(SeatDto seatDto);
}
