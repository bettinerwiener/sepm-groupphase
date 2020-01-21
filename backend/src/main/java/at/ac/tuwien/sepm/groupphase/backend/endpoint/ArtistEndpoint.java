package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.ArtistDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.ArtistMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Artist;
import at.ac.tuwien.sepm.groupphase.backend.service.ArtistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class ArtistEndpoint {

    private ArtistService artistService;
    private ArtistMapper artistMapper;

    public ArtistEndpoint(ArtistService artistService, ArtistMapper artistMapper) {
        this.artistMapper = artistMapper;
        this.artistService = artistService;
    }

    @ResponseStatus(HttpStatus.OK)
    @CrossOrigin("*")
    @GetMapping("/api/v1/artists")
    @ApiOperation(value = "Get all artists", authorizations = {@Authorization(value = "apiKey")})
    public List<ArtistDto> getAll() {
        return this.artistService.getAll().stream().
            map(artist -> this.artistMapper.artistToArtistDto(artist)).collect(Collectors.toList());
    }

}
