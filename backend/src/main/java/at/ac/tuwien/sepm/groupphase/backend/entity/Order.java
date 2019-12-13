package at.ac.tuwien.sepm.groupphase.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id")
    private Long userId;



    /**
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
     */

    public Order() {
    }

    public Order(Long userId, List<Ticket> tickets) {
        this.userId =userId;
    }

}
