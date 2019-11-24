package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.time.Duration;
import java.util.Set;

@Entity
public class Event {

    private enum Type {
        CONCERT,
        FILM,
        THEATER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, name = "abstract", length = 255)
    private String shortDescription;

    @Column(length = 511)
    private String contents;

    @Column(nullable = false)
    private Type type;

    /* think about the data type */
    @Column(nullable = false)
    private Long duration;

    @OneToMany(mappedBy = "event")
    Set<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    Set<EventPerformance> eventPerformances;

    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee;

    @ManyToMany(mappedBy = "events")
    Set<Artist> artists;
}
