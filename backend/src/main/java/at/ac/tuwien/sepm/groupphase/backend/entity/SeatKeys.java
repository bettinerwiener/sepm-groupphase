package at.ac.tuwien.sepm.groupphase.backend.entity;

import java.io.Serializable;

public class SeatKeys implements Serializable {

    private int number;
    private char row;

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
}
