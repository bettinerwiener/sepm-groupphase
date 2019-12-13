import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { EventPerformance } from '../dtos/event-performance';

@Injectable({
  providedIn: 'root'
})
export class EventPerformanceService {

  private eventPerformanceBaseUri: string = this.globals.backendUri + '/performance';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getEventPerformance(): Observable<EventPerformance[]> {
    console.log('Get all eventPerformances');
    return this.httpClient.get<EventPerformance[]>(this.eventPerformanceBaseUri);
  }

  createEventPerformance(eventPerformance: EventPerformance): Observable<EventPerformance> {
    console.log('Post eventPerformance');
    return this.httpClient.post<EventPerformance>(this.eventPerformanceBaseUri, eventPerformance);
  }
}
