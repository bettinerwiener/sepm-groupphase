package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "is_performed_at")
@Data
public class EventPerformance {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date date;

}
