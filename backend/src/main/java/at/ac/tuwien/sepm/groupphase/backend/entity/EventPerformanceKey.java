package at.ac.tuwien.sepm.groupphase.backend.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class EventPerformanceKey implements Serializable {

    private Long event;

    private Long location;

    private Date date;

    public EventPerformanceKey() {};
    public EventPerformanceKey(Long event, Long location, Date date) {
        this.event = event;
        this.location = location;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventPerformanceKey)) return false;
        EventPerformanceKey that = (EventPerformanceKey) o;
        return event.equals(that.event) &&
            location.equals(that.location) &&
            date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, location, date);
    }
}
