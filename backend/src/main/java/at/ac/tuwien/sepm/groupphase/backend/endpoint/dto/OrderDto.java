package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
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
import java.util.TimeZone;

@Data
public class OrderDto {


    Long id;

    @NotNull
    private User user;

    public OrderDto() {

    }


    private static final class OrderDtoBuilder {

        private Long id;
        private User user;

        public OrderDtoBuilder() {};

        public OrderDto.OrderDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }
        public OrderDto.OrderDtoBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public OrderDto build() {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(id);
            orderDto.setUser(user);
            return orderDto;
        }
    }

}
