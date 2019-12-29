import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { GlobalEvent } from '../dtos/global-event';
import { Room } from '../dtos/room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private roomBaseUri: string = this.globals.backendUri + '/rooms';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getRoom(): Observable<Room[]> {
    console.log('Get all rooms');
    return this.httpClient.get<Room[]>(this.roomBaseUri);
  }

  createRoom(room: Room): Observable<Room> {
    console.log('Create Room with name: ' + room.name);
    return this.httpClient.post<Room>(this.roomBaseUri, room);
  }
}
