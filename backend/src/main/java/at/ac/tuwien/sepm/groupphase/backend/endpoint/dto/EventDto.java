package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import lombok.*;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EventDto extends BaseDto {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    @NotNull
    @Size(max = 255)
    private String shortDescription;

    @Size(max = 511)
    private String contents;

    @NotNull
    private Event.Category category;

    /* think about the data type */
    @NotNull
    @Size(min = 0, max = 10)
    private Double duration;

    public static final class EventDtoBuilder {
        private Long id;
        private String title;
        private String shortDescription;
        private String contents;
        private Event.Category category;
        private Double duration;

        private EventDtoBuilder() {
        }

        public static EventDto.EventDtoBuilder anEventDto() {
            return new EventDto.EventDtoBuilder();
        }

        public EventDto.EventDtoBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public EventDto.EventDtoBuilder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public EventDto.EventDtoBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public EventDto.EventDtoBuilder withContents(String contents) {
            this.contents = contents;
            return this;
        }

        public EventDto.EventDtoBuilder withDuration(Double Duration) {
            this.duration = duration;
            return this;
        }

        public EventDto.EventDtoBuilder withCategory(Event.Category category) {
            this.category = category;
            return this;
        }

        public EventDto build() {
            EventDto eventDto = new EventDto();
            eventDto.setId(id);
            eventDto.setShortDescription(shortDescription);
            eventDto.setTitle(title);
            eventDto.setContents(contents);
            eventDto.setDuration(duration);
            eventDto.setCategory(category);
            return eventDto;
        }
    }
}
