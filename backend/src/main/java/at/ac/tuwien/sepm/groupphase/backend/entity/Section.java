package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Section {

    public enum Letter {
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
    }

    public enum PriceCategory {
        EXPENSIVE,
        CHEAP
    }

    @Id
    private Long id;

    @Column(nullable = false, name = "seats_selectable")
    private Boolean seatsSelectable;

    @Column(nullable = false, name = "letter")
    @Enumerated(EnumType.STRING)
    private Letter letter;

    @Column(nullable = false, name = "price_category")
    @Enumerated(EnumType.STRING)
    private PriceCategory priceCategory;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

}
