package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "row_letter", nullable = false)
    private String row;

    @Column (name = "seat_number", nullable = false)
    private int number;

    @ManyToOne
    @JoinColumn(name = "section")
    private Section section;

}
