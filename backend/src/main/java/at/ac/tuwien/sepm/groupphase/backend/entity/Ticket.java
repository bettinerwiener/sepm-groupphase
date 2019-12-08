package at.ac.tuwien.sepm.groupphase.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_order_id")
    private Order customer_order;


    @ManyToOne
    @JoinColumn(name = "is_performed_at_id")
    private EventPerformance performance;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private Float price;

    private String status;
}
