package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;


import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import lombok.Data;


import java.util.List;

@Data
public class TicketListWrapperDto {

    private List<Ticket> tickets;

}
