import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { EventObject } from 'src/app/dtos/event-object';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Ticket } from 'src/app/dtos/ticket';
import { Order } from 'src/app/dtos/order';
import { TicketDto } from 'src/app/dtos/ticket-dto';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.scss']
})
export class EventItemComponent implements OnInit {

  imageSource: string = 'https://dl1.cbsistatic.com/i/r/2018/08/09/b6ca69f8-f123-408c-9b1f-ea3f9cf1fb17/resize/620xauto/8787947d1d00135d3f2ed512e56bee72/concert-crowd.jpg';
  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;
  selectSeatBool: boolean = false;
  eventObject: EventObject;
  performances: Array<EventPerformance>;
  id: number = 0;
  selectedTickets: Array<Ticket> = new Array<Ticket>();
  submittedTickets: Array<TicketDto> = new Array<TicketDto>();
  load: boolean = false;
  order: Order;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TicketService
  ) { }

  selectSeats(performance: EventPerformance): void {
    performance.seatSelection = !performance.seatSelection;
  }

  ngOnInit() {
    var id: number = parseInt(this.route.snapshot.paramMap.get('id'), 10);
    this.service.getEvent(id).subscribe(
      (eventObj: EventObject) => {
        this.eventObject = eventObj;
      }
    )

    this.service.getPerformancesByEventId(id).subscribe(
      (performances: Array<EventPerformance>) => {
        this.performances = performances;

        for (let perf of performances) {
          this.service.getTicketsByPerformanceId(perf.id).subscribe(
            (tickets: Array<Ticket>) => {
              this.ticketsToArray(tickets, perf);
              this.load = true;
            }
          );
        }
      }
    );
  }

  ticketsToArray(tickets: Array<Ticket>, eventPerformance: EventPerformance) {
    eventPerformance.tickets = new Array<Array<Ticket>>();
    tickets.sort((a, b) => (a.seat.rowLetter > b.seat.rowLetter ? 1 : -1));
    var j: number = 0;
    eventPerformance.tickets.push(new Array<Ticket>());
    for (let i = 0; i < tickets.length; i++) {
      if (i > 0 && tickets[i].seat.rowLetter !== tickets[i - 1].seat.rowLetter) {
        eventPerformance.tickets.push(new Array<Ticket>());
        j++;
      }
      eventPerformance.tickets[j].push(tickets[i]);
    }

    for (let row of eventPerformance.tickets) {
      row.sort((a, b) => a.seat.seatNumber - b.seat.seatNumber);
    }
  }

  addSeat(ticket: Ticket): void {
    if (!this.selectedTickets.includes(ticket)) {
      this.selectedTickets.push(ticket);
    } else {
      var index: number = this.selectedTickets.indexOf(ticket);
      if (index > -1) {
        this.selectedTickets.splice(index, 1);
      }
    }
  }

  buyTickets(eventPerformance: EventPerformance): void {
    this.submittedTickets = new Array<TicketDto>();
    for (let ticket of this.selectedTickets) {
      this.submittedTickets.push(new TicketDto(ticket.id, null, ticket.performance, ticket.seat, ticket.status, ticket.price));
      ticket.status = 'BOUGHT';
    }

    this.service.buyTickets(this.submittedTickets).subscribe(
      (order:Order) => {
        console.log(order);
      }
    );

    this.selectedTickets = new Array<Ticket>();
  }

  reserveTickets(eventPerformance: EventPerformance): void {
    this.submittedTickets = new Array<TicketDto>();

    for (let ticket of this.selectedTickets) {
      this.submittedTickets.push(new TicketDto(ticket.id, null, ticket.performance, ticket.seat, ticket.status, ticket.price));
      ticket.status = 'RESERVED';
    }

    this.service.reserveTickets(this.submittedTickets).subscribe(
      (order:Order) => {
        console.log(order);
      }
    );

    this.selectedTickets = new Array<Ticket>();
  }
}

