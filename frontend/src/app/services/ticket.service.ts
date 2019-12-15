import { Injectable } from '@angular/core';
import { SeatplanObject } from '../dtos/seatplan-object';
import { Time } from '@angular/common';
import { Ticket } from '../dtos/ticket';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { EventObject } from '../dtos/event-object';
import { EventPerformance } from '../dtos/event-performance';
import { Seat } from '../dtos/seat';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private messageBaseUri: string = this.globals.backendUri;

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  buyTicket(id: number, event: EventObject, room: number, seat: Seat, status: string, price: number, location: string, hall: string, time: Time): Observable<Ticket> {
    var ticket: Ticket = new Ticket(id, event, room, seat, status, price, location, time);
    return this.httpClient.put<Ticket>(this.messageBaseUri, ticket);
  }

  getEvent(id: number): Observable<EventObject> {
    console.log("Loading event with id " + id);
    return this.httpClient.get<EventObject>(this.messageBaseUri + '/events/' + id);
  }

  getPerformancesByEventId(eventId: number): Observable<Array<EventPerformance>> {
    console.log("Loading performances of event with id " + eventId);
    return this.httpClient.get<Array<EventPerformance>>(this.messageBaseUri + '/performances?event=' + eventId);
  }

  getTicketsByPerformanceId(performanceId: number): Observable<Array<Ticket>> {
    console.log("Loading tickets of performances with id " + performanceId);
    return this.httpClient.get<Array<Ticket>>(this.messageBaseUri + '/tickets?performance=' + performanceId);
  }


}
