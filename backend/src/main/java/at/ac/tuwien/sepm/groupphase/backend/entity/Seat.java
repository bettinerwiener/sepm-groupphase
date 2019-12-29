package at.ac.tuwien.sepm.groupphase.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column (name = "row_letter", nullable = false)
    private String rowLetter;

    @Column (name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "section")
    private Section section;

}