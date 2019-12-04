package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, name = "postal_code")
    private Long postalCode;

    @OneToMany(mappedBy = "location")
    Set<Ticket> tickets;

    @OneToMany(mappedBy = "location")
    Set<EventPerformance> eventPerformances;

    @OneToMany(mappedBy = "location")
    Set<Room> rooms;
}
