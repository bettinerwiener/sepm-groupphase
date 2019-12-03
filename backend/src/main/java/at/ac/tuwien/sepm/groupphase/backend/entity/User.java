package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;

    @Column(nullable = false, name = "last_name", length = 50)
    private String lastName;

    @Column(nullable=false, length = 150)
    private String email;

    @Column(nullable=false, length = 255)
    private String password;

    @Column(nullable = false)
    private Boolean locked;

    @Column(nullable = false, name = "is_employee")
    private Boolean isEmployee;

    @Column(nullable = false, name = "login_count")
    private int loginCount;

    @ManyToMany
    @JoinTable (
        name = "customer_buys_ticket",
        joinColumns = @JoinColumn(name = "customer"),
        inverseJoinColumns = @JoinColumn(name = "ticket")
    )
    private Set<Ticket> tickets;

    @ManyToMany
    @JoinTable (
        name = "employee_buys_ticket",
        joinColumns = @JoinColumn(name = "employee"),
        inverseJoinColumns = @JoinColumn(name = "ticket")
    )
    private Set<Ticket> ticketsOfEmployee;

    @OneToMany(mappedBy = "user")
    private Set<CustomerNews> customerNews;

}
