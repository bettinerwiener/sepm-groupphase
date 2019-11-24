package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

/**
 * This class represents a triple relation
 * TODO: How should I represent it here?
 */
@Entity
@Table(name = "employee_adds_news")
public class EmployeeNewsEvent {

    @Embedded
    private EmployeeNewsEventKey id;

    @ManyToOne
    @MapsId("event")
    @JoinColumn(name = "event")
    private Event event;

    @ManyToOne
    @MapsId("news")
    @JoinColumn(name = "news")
    private News news;

    @ManyToOne
    @MapsId("employee")
    @JoinColumn(name = "employee")
    private Employee employee;

    public EmployeeNewsEvent() {}

    public EmployeeNewsEvent(Event event, News news, Employee employee) {
        this.event = event;
        this.news = news;
        this.employee = employee;
    }
}
