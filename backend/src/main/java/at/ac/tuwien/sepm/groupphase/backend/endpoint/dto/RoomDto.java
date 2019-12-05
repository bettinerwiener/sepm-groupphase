package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import at.ac.tuwien.sepm.groupphase.backend.entity.Ticket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
public class RoomDto extends BaseDto {

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Location location;

    @NotNull
    Set<Section> sections;

    Set<Ticket> tickets;

    Set<EventPerformance> eventPerformances;

    private static final class RoomDtoBuilder {

        private Long id;
        private String name;
        private Location location;
        private Set<Section> sections;
        private Set<Ticket> tickets;
        private Set<EventPerformance> eventPerformances;

        public RoomDtoBuilder() {};

        public RoomDto.RoomDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RoomDto.RoomDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public RoomDto.RoomDtoBuilder withLocation(Location location) {
            this.location = location;
            return this;
        }
        public RoomDto.RoomDtoBuilder withSections(Set<Section> sections) {
            this.sections = sections;
            return this;
        }
        public RoomDto.RoomDtoBuilder withTickets(Set<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }
        public RoomDto.RoomDtoBuilder withEventPerformance(Set<EventPerformance> eventPerformances) {
            this.eventPerformances = eventPerformances;
            return this;
        }

        public RoomDto build() {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(id);
            roomDto.setName(name);
            roomDto.setLocation(location);
            roomDto.setSections(sections);
            roomDto.setTickets(tickets);
            roomDto.setEventPerformances(eventPerformances);
            return roomDto;
        }
    }
}
