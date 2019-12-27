package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
public class EmployeeNewsEventKey implements Serializable {

    private Long employee;

    private Long news;

    private Long event;
}
