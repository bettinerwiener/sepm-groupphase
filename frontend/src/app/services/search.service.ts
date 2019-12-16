import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { Globals } from '../global/globals';
import { Artist } from '../dtos/artist';
import { EventLocation } from '../dtos/event-location';
import { GlobalEvent } from '../dtos/global-event';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  loadEvent(
    searchTerm: string,
    category: string,
    startDate: Date,
    endDate: Date,
    price: number,
    duration: number,
    eventLocation: EventLocation,
    artist: Artist
    ): Observable<Array<GlobalEvent>> {

    let query = '?';
    if (searchTerm) { query += `searchTerm=${searchTerm}&`; }
    if (category) { query += `category=${category}&`; }
    if (startDate) { query += `startDate=${startDate}&`; }
    if (endDate) { query += `endDate=${endDate}&`; }
    if (price) { query += `price=${Number(price)}&`; }
    if (duration) { query += `duration=${Number(duration)}&`; }
    if (eventLocation) { query += `location=${eventLocation}&`; }
    if (artist) { query += `Artist=${artist}`; }

    console.log('Get event', query);
    return this.httpClient.get<Array<GlobalEvent>>(this.globals.backendUri + '/events' + query);
  }

}
