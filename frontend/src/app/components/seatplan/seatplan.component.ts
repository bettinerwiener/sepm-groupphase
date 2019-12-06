import { Component, OnInit } from '@angular/core';
import { Seat } from '../../dtos/seat';
import { SeatplanObject } from 'src/app/dtos/seatplan-object';
import { EventObject } from 'src/app/dtos/event-object';
import { Time } from '@angular/common';

@Component({
  selector: 'app-seatplan',
  templateUrl: './seatplan.component.html',
  styleUrls: ['./seatplan.component.scss']
})
export class SeatplanComponent implements OnInit {

  constructor() { }

  /**
 * Loads the horse for the specified id
 * @param id the id of the horse
 *
private loadHorse(id: number) {
  this.seatplan.getHorseById(id).subscribe(
    (horse: Horse) => {
      this.horse = horse;
    },
    error => {
      this.defaultServiceErrorHandling(error);
    }
  );
}*/


  seatplan: SeatplanObject;
  eventObject: EventObject;


  ngOnInit() {
    //this.eventObject = new EventObject("testlocation", "Hall A", new Date())

    var row: Array<Seat> = new Array<Seat>();
    var rows: Array<Array<Seat>> = new Array<Array<Seat>>();

    for (let i = 0; i < 10; i++) {
      row = new Array<Seat>();
      for (let j = 0; j < 10; j++) {
        row.push(new Seat(i, j, (i % 5)));
      }
      rows.push(row);
    }
    this.seatplan = new SeatplanObject(10,10, rows);
  }

  select(seat: Seat): void {
    seat.selected = !seat.selected;
    console.log(seat);
  }


}
