package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This class represents a triple relation
 * TODO: How should I represent it here?
 */
@Entity
@Table(name = "employee_adds_news")
@IdClass(EmployeeNewsEventKey.class)
@Data
public class EmployeeNewsEvent {

    @Id
    @ManyToOne
    @MapsId("event")
    @JoinColumn(name = "event")
    private Event event;

    @Id
    @ManyToOne
    @MapsId("news")
    @JoinColumn(name = "news")
    private News news;

    @Id
    @ManyToOne
    @MapsId("employee")
    @JoinColumn(name = "employee")
    private User employee;

}
