import { Injectable } from '@angular/core';
import { EventNews } from '../dtos/event-news';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';

@Injectable({
  providedIn: 'root'
})
export class EventNewsService {

    eventNewsBaseUri: string = this.globals.backendUri + '/eventnews';

    constructor(private httpClient: HttpClient, private globals: Globals) { }

    /**
     * Getting all news associated with an event
     */
    getAllEventNews(): Observable<EventNews[]> {
        console.log("Getting all event news");
        return this.httpClient.get<EventNews[]>(this.eventNewsBaseUri);
    }

    /**
     * Create a news entry for an event
     */
    createEventNews(eventNews: EventNews): Observable<EventNews> {
        console.log("Creating news entry for an event");
        return this.httpClient.post<EventNews>(this.eventNewsBaseUri, eventNews);
    }
}