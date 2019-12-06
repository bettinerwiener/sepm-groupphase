package at.ac.tuwien.sepm.groupphase.backend.unittests;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper.EventMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class EventMappingTest {

    @Autowired
    EventMapper eventMapper;

    @Test
    public void whenMapEventToEventDto_EventDtoHasSameProperties() {
        Event event = new Event();
        event.setTitle("Les Misérables");
        event.setShortDescription("kenn ich nicht");
        event.setContents("une histoire cruelle");
        event.setCategory(Event.Category.FILM);
        event.setDuration(4.7);

        EventDto eventDto = eventMapper.eventToEventDto(event);

        assertAll(
            () -> assertEquals(event.getTitle(), eventDto.getTitle()),
            () -> assertEquals(event.getShortDescription(), eventDto.getShortDescription()),
            () -> assertEquals(event.getCategory(), eventDto.getCategory())
        );
    }

    @Test
    public void whenMapEventDtoToEvent_EventHasSameProperties() {
        EventDto eventDto = EventDto.EventDtoBuilder.anEventDto()
            .withTitle("Les Misérables")
            .withCategory(Event.Category.FILM)
            .withDuration(4.5)
            .withShortDescription("lajdfl")
            .withContents("kalsdjf")
            .build();

        Event event = eventMapper.eventDtoToEvent(eventDto);

        assertAll(
            () -> assertEquals(event.getTitle(), eventDto.getTitle()),
            () -> assertEquals(event.getShortDescription(), eventDto.getShortDescription()),
            () -> assertEquals(event.getCategory(), eventDto.getCategory())
        );
    }
}
