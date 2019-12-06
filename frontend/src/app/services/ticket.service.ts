import { Injectable } from '@angular/core';
import { SeatplanObject } from '../dtos/seatplan-object';
import { Time } from '@angular/common';
import { Ticket } from '../dtos/ticket';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { Globals } from '../global/globals';
import { EventObject } from '../dtos/event-object';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  private messageBaseUri: string = this.globals.backendUri + '/messages';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  buyTicket(seatplan:SeatplanObject, location:string, hall:string, time:Time): Observable<Ticket> {
    var ticket:Ticket = new Ticket(seatplan, location, hall, time);
    return this.httpClient.put<Ticket>(this.messageBaseUri, ticket);
  }

  getEvent(id:number): Observable<EventObject> {
    console.log("Loading event with id " + id);
    return this.httpClient.get<EventObject>(this.messageBaseUri + '/event/' + id);
  }
}
