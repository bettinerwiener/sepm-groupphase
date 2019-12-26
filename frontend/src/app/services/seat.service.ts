import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { Seat } from '../dtos/seat';

@Injectable({
  providedIn: 'root'
})
export class SeatService {

  private seatBaseUri: string = this.globals.backendUri + '/seats';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  createSeats(seats: Array<Seat>): Observable<Array<Seat>> {
    console.log('Create seats for room.');
    return this.httpClient.post<Array<Seat>>(this.seatBaseUri, seats);
  }

  getSeatsOfRoom(roomID: number): Observable<Array<Seat>> {
    console.log('Get seats of room ' + roomID);
    return this.httpClient.get<Array<Seat>>(this.seatBaseUri + roomID);
  }
}
