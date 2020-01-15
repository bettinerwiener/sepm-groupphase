import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {User} from '../dtos/user';
import {AuthRequest} from '../dtos/auth-request';

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

  verifyAdmin(user: AuthRequest): Observable<boolean> {
    return this.httpClient.post<boolean>(this.messageBaseUri + '/validate', user, this.httpOptions);
  }

  deleteUser(name: String): Observable<User> {
    return this.httpClient.delete<User>(this.messageBaseUri + '/delete?name=' + name, {headers: new HttpHeaders({
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*'})
    });
  }

}
