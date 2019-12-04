package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "is_performed_at")
@IdClass(EventPerformanceKey.class)
@Data
public class EventPerformance {

    @Id
    @ManyToOne
    @MapsId("event")
    @JoinColumn(name = "event")
    private Event event;

    @Id
    @ManyToOne
    @MapsId("location")
    @JoinColumn(name = "location")
    private Location location;

    @Id
    @Column(nullable = false)
    private LocalDateTime date;

}
