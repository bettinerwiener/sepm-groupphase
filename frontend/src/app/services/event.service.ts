import { Injectable } from '@angular/core';
import { GlobalEvent } from '../dtos/global-event';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  eventBaseUri: string = this.globals.backendUri + '/events';
  private toptenBaseUri: string = this.eventBaseUri + '/topten';
  private eventAllBaseUri: string = this.eventBaseUri + '/all';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  /**
   * Loads all events from the backend
   */
  getEvent(): Observable<GlobalEvent[]> {
    console.log('Get all events');
    return this.httpClient.get<GlobalEvent[]>(this.eventBaseUri + '/all');
  }

  getCreatedEvent(): Observable<GlobalEvent> {
    console.log('Get created event');
    return this.httpClient.get<GlobalEvent>(this.eventBaseUri);
  }

  getTopTenEvents(): Observable<GlobalEvent[]> {
    console.log('Get top ten events');
    return this.httpClient.get<GlobalEvent[]>(this.toptenBaseUri);
  }

  sendImage(formData: FormData, id: Number): Observable<Boolean> {
    console.log('Sending image separately');
    return this.httpClient.post<Boolean>(this.eventBaseUri + '/' + id, formData);
}

  updateImage(formData: FormData, id: Number): Observable<Boolean> {
    console.log('Sending image separately');
    return this.httpClient.post<Boolean>(this.eventAllBaseUri + '/' + id, formData);
  }

  getMinPricePerEvent(id: Number): Observable<Number> {
    console.log('Get minimum price per event');
    return this.httpClient.get<Number>(this.eventAllBaseUri + '/minprice/' + id);
  }

  getImage(id: Number) {
    console.log('Getting image for news entry with id ' + id);
    return this.httpClient.get(this.eventBaseUri + '/' + id + '/image', {observe: 'response', responseType: 'blob'});
  }

  /**
   * Creates event with following parameters:
   * @param title Title of the evevnt
   * @param category which category: film, concert, theater,
   * @param shortDescription short description, shown at the preview
   * @param contents detailed description
   * @param duration how long the event lasts
   */
  createEvent(event: GlobalEvent): Observable<GlobalEvent> {
    console.log('Post Event with title' + event.title + ', category: ' + event.category);
    return this.httpClient.post<GlobalEvent>(this.eventBaseUri, event);
  }
}
