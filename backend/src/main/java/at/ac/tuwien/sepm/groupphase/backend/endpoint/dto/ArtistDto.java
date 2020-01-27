package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@Slf4j
public class ArtistDto {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private static final class ArtistDtoBuilder {

        private Long id;
        private String firstName;
        private String lastName;

        public ArtistDtoBuilder() {};

        public ArtistDto.ArtistDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ArtistDto.ArtistDtoBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ArtistDto.ArtistDtoBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ArtistDto build() {
            ArtistDto artistDto = new ArtistDto();
            artistDto.setId(id);
            artistDto.setFirstName(firstName);
            artistDto.setLastName(lastName);
            return artistDto;
        }

    }
}
