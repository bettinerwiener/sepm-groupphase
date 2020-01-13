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
    newsBaseUri: string = this.globals.backendUri + '/news';

    constructor(private httpClient: HttpClient, private globals: Globals) { }

    /**
     * Getting all news associated with an event
     */
    getAllEventNews(): Observable<EventNews[]> {
        console.log("Getting all event news");
        return this.httpClient.get<EventNews[]>(this.eventNewsBaseUri);
    }

    getNewsById(id: number): Observable<EventNews> {
        console.log("Get event news by id " + id);
        return this.httpClient.get<EventNews>(this.newsBaseUri + '/' + id);
    }

    getEventForNews(id:number):Observable<Array<EventNews>> {
        console.log("Get event for news " + id);
        return this.httpClient.get<Array<EventNews>>(this.eventNewsBaseUri + '?news=' + id);
      }
    
    /**
     * Create a news entry for an event
     */
    createEventNews(eventNews: EventNews): Observable<EventNews> {
        console.log("Creating news entry for an event");
        return this.httpClient.post<EventNews>(this.eventNewsBaseUri, eventNews);
    }

    sendImage(formData: FormData, id: Number): Observable<Boolean> {
        console.log("Sending image separately");
        return this.httpClient.post<Boolean>(this.newsBaseUri + '/' + id, formData);
    }

    getImage(id: Number): Observable<Blob> {
        console.log("Getting image for news entry with id " + id);
        return this.httpClient.get<Blob>(this.newsBaseUri + '/' + id + '/image');
    }
}