import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Seat } from '../../dtos/seat';
import { SeatplanObject } from 'src/app/dtos/seatplan-object';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Ticket } from 'src/app/dtos/ticket';

@Component({
  selector: 'app-seatplan',
  templateUrl: './seatplan.component.html',
  styleUrls: ['./seatplan.component.scss']
})
export class SeatplanComponent implements OnInit {

  constructor() { }

  @Input() eventPerformance: EventPerformance;
  @Output() selectedSeat: EventEmitter<Ticket> = new EventEmitter();

  stagewidth:string;

  ngOnInit() {
    this.stagewidth = this.eventPerformance.tickets[0].length*39 + "px";
    
  }

  select(ticket: Ticket): void {
    ticket.selected = !ticket.selected;
    this.selectedSeat.emit(ticket);
  }


}
