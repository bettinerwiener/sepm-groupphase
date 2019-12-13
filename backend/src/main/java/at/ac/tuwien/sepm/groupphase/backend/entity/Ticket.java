package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
@Data
public class Ticket {
    public enum Status {
        AVAILABLE,
        RESERVED,
        BOUGHT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private Order customerOrder;

    @ManyToOne
    @JoinColumn(name = "is_performed_at_id")
    private EventPerformance performance;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private Float price;

    @Enumerated(EnumType.STRING)
    private Status status;
}
