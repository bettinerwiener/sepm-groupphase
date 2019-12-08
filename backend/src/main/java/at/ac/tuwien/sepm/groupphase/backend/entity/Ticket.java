package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ticket {

    public enum Status {
        AVAILABLE,
        RESERVED,
        BOUGHT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @ManyToOne
    @JoinTable (
        name = "user_buys_ticket",
        joinColumns = @JoinColumn(name = "user"),
        inverseJoinColumns = @JoinColumn(name = "ticket")
    )
    private User user;

    @Column(nullable = false)
    private Double price;
}
