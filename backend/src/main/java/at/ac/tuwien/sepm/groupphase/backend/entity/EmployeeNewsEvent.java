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

}
