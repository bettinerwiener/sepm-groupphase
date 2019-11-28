package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
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

    @Column(length = 511)
    private String contents;

    @Column(nullable = false)
    private Category category;

    /* think about the data type */
    @Column(nullable = false)
    private  Double duration;

    @OneToMany(mappedBy = "event")
    Set<Ticket> tickets;

    @OneToMany(mappedBy = "event")
    Set<EventPerformance> eventPerformances;

    @OneToMany(mappedBy = "event")
    Set<EmployeeNewsEvent> employeeNewsEvents;

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @ManyToMany(mappedBy = "events")
    Set<Artist> artists;

    public Event() {}

    public Event(String title, String shortDescription, String contents, Category category, Double duration) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.category = category;
        this.duration = duration;
    }

    public Event(Long id, String title, String shortDescription, String contents,
                 Category category, Double duration) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.category = category;
        this.duration = duration;
    }


    public Event(String title, String shortDescription, String contents, Category category, Double duration,
                 Set<Ticket> tickets, Set<EventPerformance> eventPerformances,
                 Set<EmployeeNewsEvent> employeeNewsEvents, Employee employee, Set<Artist> artists) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contents = contents;
        this.category = category;
        this.duration = duration;
        this.tickets = tickets;
        this.eventPerformances = eventPerformances;
        this.employeeNewsEvents = employeeNewsEvents;
        this.employee = employee;
        this.artists = artists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<EventPerformance> getEventPerformances() {
        return eventPerformances;
    }

    public void setEventPerformances(Set<EventPerformance> eventPerformances) {
        this.eventPerformances = eventPerformances;
    }

    public Set<EmployeeNewsEvent> getEmployeeNewsEvents() {
        return employeeNewsEvents;
    }

    public void setEmployeeNewsEvents(Set<EmployeeNewsEvent> employeeNewsEvents) {
        this.employeeNewsEvents = employeeNewsEvents;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}
