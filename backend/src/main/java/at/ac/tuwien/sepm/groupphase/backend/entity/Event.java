package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames={"title", "category"})
)
@Data
public class Event {

    public enum Category {
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
    @Enumerated(EnumType.STRING)
    private Category category;

    /* think about the data type */
    @Column(nullable = false)
    private  Double duration;

    @OneToMany(mappedBy = "event")
    Set<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    Set<EventPerformance> eventPerformances;

    @OneToMany(mappedBy = "event")
    Set<EmployeeNewsEvent> employeeNewsEvents;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private User employee;

    @ManyToMany(mappedBy = "events")
    Set<Artist> artists;

}
