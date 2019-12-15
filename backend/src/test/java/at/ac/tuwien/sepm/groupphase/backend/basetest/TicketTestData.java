package at.ac.tuwien.sepm.groupphase.backend.basetest;

import at.ac.tuwien.sepm.groupphase.backend.entity.EventPerformance;
import at.ac.tuwien.sepm.groupphase.backend.entity.Order;
import at.ac.tuwien.sepm.groupphase.backend.entity.Seat;

import java.util.ArrayList;
import java.util.List;

public interface TicketTestData {


        Long ID = 1L;

        EventPerformance EVENT_PERFORMANCE = new EventPerformance();

        Seat SEAT = new Seat();

        Float PRICE = 200f;

}
