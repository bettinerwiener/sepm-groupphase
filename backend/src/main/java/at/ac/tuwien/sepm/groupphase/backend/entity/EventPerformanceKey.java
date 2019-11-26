package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EventPerformanceKey implements Serializable {

    @Column(name = "event")
    private Long event;

    @Column(name = "location")
    private Long location;

    public EventPerformanceKey() {};
    public EventPerformanceKey(Long event, Long location) {
        this.event = event;
        this.location = location;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }

    public Long getLocation() {
        return location;
    }

    public void setLocation(Long location) {
        this.location = location;
    }
}
