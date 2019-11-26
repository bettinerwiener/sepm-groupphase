package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

@Entity
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

    public Seat() {};
    public Seat(int number, String row, Section section) {
        this.number = number;
        this.row = row;
        this.section = section;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
