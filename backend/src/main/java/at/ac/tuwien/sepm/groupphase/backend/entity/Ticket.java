package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Ticket {

    private enum Status {
        AVAILABLE,
        RESERVED,
        BOUGHT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;

    @ManyToMany(mappedBy = "tickets")
    private Set<User> employees;

    @ManyToMany(mappedBy = "tickets")
    private Set<User> users;
}
