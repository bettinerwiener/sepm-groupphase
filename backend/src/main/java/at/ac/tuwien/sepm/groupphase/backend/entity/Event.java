package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@SqlResultSetMapping(name="Events",
    entities={
        @EntityResult(entityClass=at.ac.tuwien.sepm.groupphase.backend.entity.Event.class, fields={
            @FieldResult(name="id", column="id"),
            @FieldResult(name="title", column="title"),
            @FieldResult(name="shortDescription", column="abstract"),
            @FieldResult(name="contents", column="contents"),
            @FieldResult(name="category", column ="category"),
            @FieldResult(name="duration", column="duration"),
            @FieldResult(name="employee", column="employee")
        })
    },
    columns={}
)
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

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private User employee;

    @ManyToOne
    @JoinTable(
        name = "artist_creates_event",
        joinColumns = @JoinColumn(name = "artist"),
        inverseJoinColumns = @JoinColumn(name = "event"))
    Artist artists;

}
