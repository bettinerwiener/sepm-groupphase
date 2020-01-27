package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.LocationDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface LocationMapper {

    @Named("location")
    LocationDto locationToLocationDto(Location location);
    Location locationDtoToLocation(LocationDto locationDto);

}
