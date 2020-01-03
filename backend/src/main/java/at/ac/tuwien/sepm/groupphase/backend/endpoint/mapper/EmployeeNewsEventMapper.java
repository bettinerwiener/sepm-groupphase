package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EmployeeNewsEventDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmployeeNewsEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper
public interface EmployeeNewsEventMapper {

    @Named("employeeNewsEvent")
    EmployeeNewsEvent empNewsEventDtoToEmpNewsEvent(EmployeeNewsEventDto employeeNewsEventDto);

    EmployeeNewsEventDto empNewsEventToEmpNewsEventDto(EmployeeNewsEvent employeeNewsEvent);

    List<EmployeeNewsEventDto> eNEListToENEDtoList(List<EmployeeNewsEvent> employeeNewsEvents);

    List<EmployeeNewsEvent> eNEDtoListToENEList(List<EmployeeNewsEventDto> employeeNewsEventDtos);
}
