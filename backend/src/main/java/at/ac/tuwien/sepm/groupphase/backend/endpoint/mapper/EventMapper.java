package at.ac.tuwien.sepm.groupphase.backend.endpoint.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EventDto;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventDto EntityToDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getShortDescription(), event.getContents(),
            event.getCategory(), event.getDuration());
    }

    public Event DtoToEntity(EventDto eventDto) {
        if (eventDto.getId() != null)
        {
            return new Event(eventDto.getTitle(), eventDto.getShortDescription(), eventDto.getContents(),
                eventDto.getCategory(), eventDto.getDuration());
        } else {
            return new Event(eventDto.getId(), eventDto.getTitle(), eventDto.getShortDescription(), eventDto.getContents(),
                eventDto.getCategory(), eventDto.getDuration());
        }
    }
}

