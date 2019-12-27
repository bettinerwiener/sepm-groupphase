package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 100, name = "abstract")
    private String shortDescription;

    @Column(nullable = false, name = "published_at")
    private LocalDateTime publishedAt;

    @Column(length = 1024)
    private String image;

    @OneToMany(mappedBy = "news")
    private Set<EmployeeNewsEvent> employeeNewsEvents;

    @OneToMany(mappedBy = "news", cascade = CascadeType.PERSIST)
    private Set<CustomerNews> customerNews;

}
