package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name", length = 50)
    private String firstName;

    @Column(nullable = false, name = "last_name", length = 50)
    private String lastName;

    @Column(nullable=false, length = 150)
    private String email;

    @Column(nullable=false, length = 20)
    private String password;

    @Column(nullable = false)
    private Boolean locked;

    @ManyToMany
    @JoinTable (
        name = "customer_buys_ticket",
        joinColumns = @JoinColumn(name = "customer"),
        inverseJoinColumns = @JoinColumn(name = "ticket")
    )
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerNews> customerNews;


    public Customer() {
    }

    public Customer(String firstName, String lastName, Boolean locked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.locked = locked;
    }

    public Customer(String firstName, String lastName, String email, String password) {
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

    public Boolean getLocked() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId().equals(customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPassword(), getLocked(), getTickets());
    }
}
