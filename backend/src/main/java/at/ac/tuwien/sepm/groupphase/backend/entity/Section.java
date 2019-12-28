package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "seats_selectable")
    private Boolean seatsSelectable;

    @Column(nullable = false, name = "letter")
    @Enumerated(EnumType.STRING)
    private Letter letter;

    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;

}