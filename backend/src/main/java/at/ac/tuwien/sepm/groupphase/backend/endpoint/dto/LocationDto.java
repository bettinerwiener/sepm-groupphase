package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString

public class LocationDto {

    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private Long postalCode;

    private static final class LocationDtoBuilder {

        private Long id;
        private String name;
        private String street;
        private String city;
        private Long postalCode;

        public LocationDto.LocationDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }
        public LocationDto.LocationDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }
        public LocationDto.LocationDtoBuilder withStreet(String street) {
            this.street = street;
            return this;
        }
        public LocationDto.LocationDtoBuilder withCity(String city) {
            this.city= city;
            return this;
        }
        public LocationDto.LocationDtoBuilder withPostalCode(Long postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public LocationDto build() {
            LocationDto locationDto = new LocationDto();
            locationDto.setId(id);
            locationDto.setName(name);
            locationDto.setStreet(street);
            locationDto.setCity(city);
            locationDto.setPostalCode(postalCode);
            return locationDto;
        }
    }
}
