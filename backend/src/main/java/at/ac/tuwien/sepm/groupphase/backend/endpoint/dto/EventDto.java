package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.entity.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public EventDto() {}

    public EventDto(String title, String shortDescription,
                    String contents, Event.Category category, Double duration) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.category = category;
        this.duration = duration;
    }

    public EventDto(Long id, String title, String shortDescription,
                    String contents, Event.Category category, Double duration) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.category = category;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Event.Category getCategory() {
        return category;
    }

    public void setCategory(Event.Category category) {
        this.category = category;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

}
