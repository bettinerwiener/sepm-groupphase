package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

@Entity
@IdClass(SeatKeys.class)
public class Seat {

    @Id
    private int number;

    @Id
    private char row;

    @ManyToOne
    @JoinColumn(name = "section")
    private Section section;

    public Seat() {};
    public Seat(int number, char row, Section section) {
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

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
