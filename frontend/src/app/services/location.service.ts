import { Injectable } from '@angular/core';
import { Globals } from '../global/globals';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EventLocation } from '../dtos/event-location';


@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private locationBaseUri: string = this.globals.backendUri + '/locations';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getLocation(): Observable<EventLocation[]> {
    console.log('Get all locations');
    return this.httpClient.get<EventLocation[]>(this.locationBaseUri);
  }

  createLocation(location: EventLocation): Observable<EventLocation> {
    console.log('Post Location with name ' + location.name);
    return this.httpClient.post<EventLocation>(this.locationBaseUri, location);
  }
}
