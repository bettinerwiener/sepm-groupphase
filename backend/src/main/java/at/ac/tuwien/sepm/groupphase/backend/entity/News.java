package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class News {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String entry;

    @OneToMany(mappedBy = "news")
    private Set<EmployeeNewsEvent> employeeNewsEvents;

    @OneToMany(mappedBy = "news", cascade = CascadeType.PERSIST)
    private Set<CustomerNews> customerNews;


    public News() {};
    public News(String entry) {
        this.entry = entry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Set<EmployeeNewsEvent> getEmployeeNewsEvents() {
        return employeeNewsEvents;
    }

    public void setEmployeeNewsEvents(Set<EmployeeNewsEvent> employeeNewsEvents) {
        this.employeeNewsEvents = employeeNewsEvents;
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
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return getId().equals(news.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEntry());
    }
}
