package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
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


    public User() {
    }

    public User(String email, String password, Boolean locked) {
        this.email = email;
        this.password = password;
        this.locked = locked;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.locked = false;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<CustomerNews> getCustomerNews() {
        return customerNews;
    }

    public void setCustomerNews(Set<CustomerNews> customerNews) {
        this.customerNews = customerNews;
    }

    public Boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(Boolean employee) {
        isEmployee = employee;
    }

    public Set<Ticket> getTicketsOfEmployee() {
        return ticketsOfEmployee;
    }

    public void setTicketsOfEmployee(Set<Ticket> ticketsOfEmployee) {
        this.ticketsOfEmployee = ticketsOfEmployee;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPassword());
    }
}
