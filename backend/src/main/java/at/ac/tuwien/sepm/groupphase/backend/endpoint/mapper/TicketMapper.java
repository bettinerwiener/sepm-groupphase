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


    /**
     * Maps a Ticket to a TicketDto
     * @param ticket the Ticket to be mapped
     * @return the TicketDto with same values as ticket
     */
    TicketDto ticketToTicketDto(Ticket ticket);

    /**
     * Maps a List of Tickets to a List of TicketDtos
     * @param tickets the List of Tickets to be mapped
     * @return the List of TicketDtos with same values as tickets
     */
    List<TicketDto> ticketToTicketDto(List<Ticket> tickets);



    /**
     * Maps a TicketDto to a Ticket
     * @param ticketDto the TicketDto to be mapped
     * @return the Ticket with same values as ticketDto
     */
    Ticket ticketDtoToTicket(TicketDto ticketDto);

    /**
     * Maps a List of TicketDtos to a List of Tickets
     * @param ticketDtos the List of TicketDtos to be mapped
     * @return the List of Tickets with same values as ticketDtos
     */
    List<Ticket> ticketDtoToTicket(List<TicketDto> ticketDtos);



}
