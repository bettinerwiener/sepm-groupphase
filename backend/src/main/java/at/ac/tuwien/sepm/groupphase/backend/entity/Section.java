package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Section {

    private enum Letter {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
    }

    private enum PriceCategory {
        EXPENSIVE,
        CHEAP
    }

    @Id
    private Letter id;

    @Column(nullable = false, name = "seats_selectable")
    private Boolean seatsSelectable;

    @Column(nullable = false, name = "price_category")
    private PriceCategory priceCategory;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

    @OneToMany(mappedBy = "section")
    Set<Seat> seats;

}
