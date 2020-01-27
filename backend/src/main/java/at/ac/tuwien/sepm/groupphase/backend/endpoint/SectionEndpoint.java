package at.ac.tuwien.sepm.groupphase.backend.endpoint;

    import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SectionDto;
    import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.SectionMapper;
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
@RequestMapping(value = "/api/v1/sections")
@Slf4j
public class SectionEndpoint {

    private SectionMapper sectionMapper;
    private SectionService sectionService;
    private RoomService roomService;

    public SectionEndpoint(SectionMapper sectionMapper, SectionService sectionService, RoomService roomService) {
        this.sectionMapper = sectionMapper;
        this.sectionService = sectionService;
        this.roomService = roomService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all sections", authorizations = {@Authorization(value = "apiKey")})
    public List<SectionDto> getAll() {
        return this.sectionService.getAll().stream().map(section -> this.sectionMapper.sectionToSectionDto(section))
            .collect(Collectors.toList());
    }

    @GetMapping(value = "/room/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get section by room ID", authorizations = {@Authorization(value = "apiKey")})
    public List<SectionDto> getSeatsBySectionId(@PathVariable Long id) {
        return this.sectionService.findByRoom(this.roomService.findById(id)).stream().map(section -> this.sectionMapper.sectionToSectionDto(section))
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create various sections", authorizations = {@Authorization(value = "apiKey")})
    public List<SectionDto> createSections(@RequestBody List<SectionDto> sectionDtoArray) {
        System.out.println("sections are here");
        SectionDto createdSectionDto;
        List<SectionDto> sectionDtos = new ArrayList<>();
        for (SectionDto sectionDto : sectionDtoArray) {
            createdSectionDto = sectionMapper.sectionToSectionDto(sectionService
                .create(sectionMapper.sectionDtoToSection(sectionDto)));
            sectionDtos.add(createdSectionDto);
        }
        return sectionDtos;
    }
}
