package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class SeatDto extends BaseDto {

    @Id
    private Long id;

    @NotNull
    private Integer seatNumber;

    @NotNull
    private Character rowLetter;

    @NotNull
    private Integer section;

    private static final class SeatDtoBuilder {

        private Long id;
        private Integer seatNumber;
        private Character rowLetter;
        private Integer section;

        public SeatDtoBuilder() {};

        public SeatDto.SeatDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SeatDto.SeatDtoBuilder withSeatNumber(Integer seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }
        public SeatDto.SeatDtoBuilder withRowLetter(Character rowLetter) {
            this.rowLetter = rowLetter;
            return this;
        }
        public SeatDto.SeatDtoBuilder withSection(Integer section) {
            this.section = section;
            return this;
        }

        public SeatDto build() {
            SeatDto seatDto = new SeatDto();
            seatDto.setId(id);
            seatDto.setSeatNumber(seatNumber);
            seatDto.setRowLetter(rowLetter);
            seatDto.setSection(section);
            return seatDto;
        }
    }
}
