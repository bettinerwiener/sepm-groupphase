package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(nullable = false, name = "perf_date")
    private LocalDateTime date;

    @Column(nullable = false)
    private Float price;

}
