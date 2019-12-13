import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { Globals } from '../global/globals';

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
    location: any,
    artist: Artist
    ): Observable<Array<Event>> {

    let query = '?';
    if (searchTerm) { query += `searchTerm=${searchTerm}&`; }
    if (category) { query += `category=${category}`; }
    if (startDate) { query += `startDate=${startDate}`; }
    if (endDate) { query += `endDate=${endDate}`; }
    if (price) { query += `price=${Number(price)}`; }
    if (duration) { query += `duration=${Number(duration)}`; }
    if (location) { query += `location=${location}`; }
    if (artist) { query += `Artist=${artist}`; }

    console.log('Get event');
    return this.httpClient.get<Array<Event>>(this.globals.backendUri + '/events' + query);
  }

}
