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

    @Column(nullable = false, length = 511)
    private String entry;

    @Column(nullable = false, length = 127)
    private String title;

    @Column(nullable = false, length = 255, name = "abstract")
    private String shortDescription;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(nullable = true, length = 1024)
    private String image;

    @OneToMany(mappedBy = "news", cascade = CascadeType.PERSIST)
    private Set<CustomerNews> customerNews;

}
