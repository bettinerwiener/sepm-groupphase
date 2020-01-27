package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.ArtistDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface ArtistMapper {

    @Named("artist")
    ArtistDto artistToArtistDto(Artist artist);
    Artist artistDtoToArtist(ArtistDto artistDto);
}
