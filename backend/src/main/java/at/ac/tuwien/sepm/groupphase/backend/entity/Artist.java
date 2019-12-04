package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;

    @Column(nullable = false, name = "last_name", length = 50)
    private String lastName;

    @ManyToMany
    @JoinTable(name = "artist_creates_event",
        joinColumns = @JoinColumn(name = "artist"),
        inverseJoinColumns = @JoinColumn(name = "event"))
    Set<Event> events;

}
