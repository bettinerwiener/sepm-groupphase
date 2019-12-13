import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import {Ticket} from '../../dtos/ticket';
import { EventLocation } from 'src/app/dtos/event-location';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;

  title: string;
  @Input() description: string;
  @Input() date: Date;
  @Input() eventLocation: EventLocation;
  @Input() number: number;
  @Input() status: string;
  @Input() price: number;
  @Input() tickets: Ticket[];

  constructor() { }

  ngOnInit() {
    console.log(this.tickets);
    this.title = this.tickets[0].performance.event.title;
    this.description = this.tickets[0].performance.event.shortDescription;
    this.date = this.tickets[0].performance.date;
    this.eventLocation = this.tickets[0].performance.room.eventLocation;
    this.price = this.calculatePrice();
    this.number = this.tickets.length;
    this.status = this.tickets[0].status;
  }

  calculatePrice(): number {
    let price = 0;
    this.tickets.forEach(ticket => {
      price += ticket.price;
    });
    return price;
  }

}
