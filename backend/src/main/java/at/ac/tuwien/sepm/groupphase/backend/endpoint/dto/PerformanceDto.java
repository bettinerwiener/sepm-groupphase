package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@Getter
@Setter
@Slf4j
@ToString
public class PerformanceDto {

    Long id;

    @NotNull
    private Event event;
    @NotNull
    private Room room;
    @NotNull
    private LocalDateTime date;

    private static final class PerformanceDtoBuilder {

        private Long id;
        private Event event;

        private Room room;

        private LocalDateTime date;

        public PerformanceDtoBuilder() {};

        public PerformanceDto.PerformanceDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }
        public PerformanceDto.PerformanceDtoBuilder withEvent(Event event) {
            this.event = event;
            return this;
        }
        public PerformanceDto.PerformanceDtoBuilder withRoom(Room room) {
            this.room = room;
            return this;
        }
        public PerformanceDto.PerformanceDtoBuilder withDate(String date) {
            this.date = LocalDateTime.parse(date);
            return this;
        }

        public PerformanceDto build() {
            PerformanceDto performanceDto = new PerformanceDto();
            performanceDto.setId(id);
            performanceDto.setEvent(event);
            performanceDto.setRoom(room);
            performanceDto.setDate(date);
            return performanceDto;
        }
    }
}
