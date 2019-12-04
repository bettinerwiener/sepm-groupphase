package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;

    @Column(nullable = false, name = "last_name", length = 50)
    private String lastName;

    @Column(nullable=false, length = 150)
    private String username;

    @Column(nullable=false, length = 20)
    private String password;

    @OneToMany(mappedBy = "employee")
    Set<Event> events;

    @OneToMany(mappedBy = "employee")
    Set<EmployeeNewsEvent> employeeNewsEvents;

    @ManyToMany
    @JoinTable (
        name = "employee_buys_ticket",
        joinColumns = @JoinColumn(name = "employee"),
        inverseJoinColumns = @JoinColumn(name = "ticket")
    )
    Set<Ticket> tickets;

}
