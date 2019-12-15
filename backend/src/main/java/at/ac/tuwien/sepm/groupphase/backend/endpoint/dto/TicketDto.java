package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TicketDto {



    private Long id;


    private Order customerOrder;


    private EventPerformance performance;


    private Seat seat;

    private Float price;

    @Enumerated(EnumType.STRING)
    private Ticket.Status status;

}
