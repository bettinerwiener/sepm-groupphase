package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import lombok.Data;

@Data
public class TicketDto {

    public enum Status {
        AVAILABLE,
        RESERVED,
        BOUGHT
    }

    private Long id;

    private Status status;

    private Event event;

    private Location location;

    private Seat seat;

    private User user;
}
