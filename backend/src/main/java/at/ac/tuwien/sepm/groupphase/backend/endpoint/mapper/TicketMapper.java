package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TicketDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface TicketMapper {


    @Named("ticket")
    TicketDto ticketToTicketDto(Ticket ticket);

    @IterableMapping(qualifiedByName = "ticket")
    List<TicketDto> ticketToTicketDto(List<Ticket> tickets);

    Ticket ticketDtoToTicket(TicketDto ticketDto);

    List<Ticket> ticketDtoToTicket(List<TicketDto> ticketDtos);

}
