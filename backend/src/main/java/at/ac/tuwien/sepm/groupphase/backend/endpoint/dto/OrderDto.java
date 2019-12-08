package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Data
public class OrderDto {


    Long id;

    @NotNull
    private Long userId;


    private List<Ticket> tickets;



    public OrderDto() {

    }

    public List<Ticket>  getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return userId;
    }

    public void setUser(Long userId) {
        this.userId = userId;
    }


    private static final class OrderDtoBuilder {

        private Long id;
        private Long userId;
        private List<Ticket> tickets;
        public OrderDtoBuilder() {};

        public OrderDto.OrderDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }
        public OrderDto.OrderDtoBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public OrderDto.OrderDtoBuilder withTickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public OrderDto build() {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(id);
            orderDto.setUser(userId);
            orderDto.setTickets(tickets);
            return orderDto;
        }
    }

}
