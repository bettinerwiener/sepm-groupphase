package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "is_performed_at")
@IdClass(EventPerformanceKey.class)
@Data
public class EventPerformance {

    @Id
    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @Id
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @Id
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date date;

}
