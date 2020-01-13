package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_news")
@IdClass(CustomerNewsKey.class)
@Data
public class CustomerNews {

    @Id
    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user")
    private User user;

    @Id
    @ManyToOne
    @MapsId("news")
    @JoinColumn(name = "news")
    private News news;

    @Column(nullable=false, name="read")
    private Boolean read;

}
