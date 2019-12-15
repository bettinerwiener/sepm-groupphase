import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {User} from "../dtos/user";

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private messageBaseUri: string = this.globals.backendUri + '/admin';
  private httpOptions = {
    headers: new HttpHeaders({'access-control-allow-origin' :'*'})
  };

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  searchUsersByName(name: String): Observable<User[]> {
    return this.httpClient.get<User[]>(this.messageBaseUri + '/search?name=' + name);
  }

  getUserByName(name: String): Observable<User> {
    return this.httpClient.get<User>(this.messageBaseUri + '/user?name=' + name);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(this.messageBaseUri + '/edit', user,
      {headers: new HttpHeaders({
      'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*'})
    });
  }

}
