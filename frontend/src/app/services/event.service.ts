import { Injectable } from '@angular/core';
import { Event } from '../dtos/event';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventBaseUri: string = this.globals.backendUri + '/events';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  /**
   * Loads all events from the backend
   */
  getEvents(): Observable<Event[]> {
    console.log('Get all events');
    return this.httpClient.get<Event[]>(this.eventBaseUri);
  }

  /**
   * Creates a Event with following params:
   * @param title Title of the Event
   * @param description Description of the Event
   * @param date Date on which this Event takes Place
   * @param location Location where this Event takes place
   * @param price How much this event costs
   */
  createEvent(title: string, description: string, date: Date, duration: number, location: string, category: string): Observable<Event> {
    console.log('Post Event with title ' + title);
    const event: Event = new Event(title, description, date, duration, location, category);
    return this.httpClient.post<Event>(this.eventBaseUri, event);
  }
}
