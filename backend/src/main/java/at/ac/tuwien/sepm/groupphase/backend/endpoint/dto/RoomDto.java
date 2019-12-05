package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

    private static final class RoomDtoBuilder {

        private Long id;
        private String name;
        private Location location;

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

        public RoomDto build() {
            RoomDto roomDto = new RoomDto();
            roomDto.setId(id);
            roomDto.setName(name);
            roomDto.setLocation(location);
            return roomDto;
        }
    }
}
