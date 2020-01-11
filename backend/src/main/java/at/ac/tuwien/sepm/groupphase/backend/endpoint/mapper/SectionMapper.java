package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.SectionDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface SectionMapper {

    @Named("section")
    SectionDto sectionToSectionDto(Section section);

    Section sectionDtoToSection(SectionDto sectionDto);
}
