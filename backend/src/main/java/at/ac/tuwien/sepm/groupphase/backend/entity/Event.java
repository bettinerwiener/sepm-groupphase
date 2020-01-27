package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.entity.Artist;
import at.ac.tuwien.sepm.groupphase.backend.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.HashSet;
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

    @Column(length = 1023)
    private String contents;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    /* think about the data type */
    @Column(nullable = false)
    private  Double duration;

    @ManyToOne
    @JoinColumn(name = "employee")
    User employee;

    @Lob
    @Column(nullable = true, columnDefinition = "BLOB")
    private byte[] image;

}
