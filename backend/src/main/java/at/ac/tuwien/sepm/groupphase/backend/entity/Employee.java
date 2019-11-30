package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
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


    public Employee() {};

    public Employee(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<EmployeeNewsEvent> getEmployeeNewsEvents() {
        return employeeNewsEvents;
    }

    public void setEmployeeNewsEvents(Set<EmployeeNewsEvent> employeeNewsEvents) {
        this.employeeNewsEvents = employeeNewsEvents;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
