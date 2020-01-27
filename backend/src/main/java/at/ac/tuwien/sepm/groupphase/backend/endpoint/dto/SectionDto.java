package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.Location;
import at.ac.tuwien.sepm.groupphase.backend.entity.Room;
import at.ac.tuwien.sepm.groupphase.backend.entity.Section;
import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
public class SectionDto extends BaseDto {

    @Id
    private Long id;

    @NotNull
    private Section.Letter letter;

    @NotNull
    private Room room;

    @NotNull
    private Boolean seatsSelectable;

    @NotNull
    private Float priceFactor;

    private static final class SectionDtoBuilder {

        private Long id;
        private Section.Letter letter;
        private Room room;
        private Boolean seatsSelectable;
        private Float priceFactor;

        public SectionDtoBuilder() {};

        public SectionDto.SectionDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SectionDto.SectionDtoBuilder withLetter(Section.Letter letter) {
            this.letter = letter;
            return this;
        }
        public SectionDto.SectionDtoBuilder withRoom(Room room) {
            this.room = room;
            return this;
        }
        public SectionDto.SectionDtoBuilder withSeatsSelectable(Boolean seatsSelectable) {
            this.seatsSelectable = seatsSelectable;
            return this;
        }
        public SectionDto.SectionDtoBuilder withPriceFactor(Float priceFactor) {
            this.priceFactor = priceFactor != null ? priceFactor : 1.0f;
            return this;
        }

        public SectionDto build() {
            SectionDto sectionDto = new SectionDto();
            sectionDto.setId(id);
            sectionDto.setLetter(letter);
            sectionDto.setRoom(room);
            sectionDto.setSeatsSelectable(seatsSelectable);
            sectionDto.setPriceFactor(priceFactor);
            return sectionDto;
        }
    }


}
