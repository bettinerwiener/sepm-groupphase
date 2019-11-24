package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "is_performed_at")
public class EventPerformance {

    @Embedded
    private EventPerformanceKey id;

    @ManyToOne
    @MapsId("event")
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @MapsId("location")
    @JoinColumn(name = "location")
    private Location location;


    @Column(nullable = false)
    private Date date;

    public EventPerformance() {}

    public EventPerformance(EventPerformanceKey id, Event event, Location location, Date date) {
        this.id = id;
        this.event = event;
        this.location = location;
        this.date = date;
    }

    public EventPerformanceKey getId() {
        return id;
    }

    public void setId(EventPerformanceKey id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
