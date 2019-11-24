package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
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
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H
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

    public Section() {};
    public Section(Letter id, Boolean seatsSelectable, PriceCategory priceCategory, Room room) {
        this.id = id;
        this.seatsSelectable = seatsSelectable;
        this.priceCategory = priceCategory;
        this.room = room;
    }

    public Letter getId() {
        return id;
    }

    public void setId(Letter id) {
        this.id = id;
    }

    public Boolean getSeatsSelectable() {
        return seatsSelectable;
    }

    public void setSeatsSelectable(Boolean seatsSelectable) {
        this.seatsSelectable = seatsSelectable;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
