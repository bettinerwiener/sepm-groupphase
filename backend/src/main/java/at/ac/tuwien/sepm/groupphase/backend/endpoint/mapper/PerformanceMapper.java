package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.PerformanceDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public interface PerformanceMapper {
    @Named("event_performance")
    PerformanceDto performanceToPerformanceDto(EventPerformance performance);
    EventPerformance performanceDtoToPerformance(PerformanceDto performanceDto);

}
