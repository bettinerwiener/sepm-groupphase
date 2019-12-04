package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
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

}
