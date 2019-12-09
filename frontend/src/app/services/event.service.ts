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
  private toptenBaseUri: string = this.eventBaseUri + '/topten';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  /**
   * Loads all events from the backend
   */
  getEvent(): Observable<Event[]> {
    console.log('Get all events');
    return this.httpClient.get<Event[]>(this.eventBaseUri);
  }

  getTopTenEvents(): Observable<Event[]> {
    console.log('Get top ten events');
    return this.httpClient.get<Event[]>(this.toptenBaseUri);
  }

  /**
   * Creates event with following parameters:
   * @param title Title of the evevnt
   * @param category which category: film, concert, theater,
   * @param shortDescription short description, shown at the preview
   * @param contents detailed description
   * @param duration how long the event lasts
   */
  createEvent(event: Event): Observable<Event> {
    console.log('Post Event with title ' + event.title);
    return this.httpClient.post<Event>(this.eventBaseUri, event);
  }
}
