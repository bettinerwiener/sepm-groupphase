import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../dtos/user';
import {AuthRequest} from '../dtos/auth-request';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private messageBaseUri: string = this.globals.backendUri + '/user';
  constructor(private httpClient: HttpClient, private globals: Globals) { }

  /**
   * Persists new user to the backend
   * @param user to persist
   */
  newUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.messageBaseUri + '/register', user);
  }

  getProfile(): Observable<User> {
    return this.httpClient.get<User>(this.messageBaseUri + '/profile');
  }

  updateProfile(user: User): Observable<User> {
    return this.httpClient.post<User>(this.messageBaseUri + '/update', user);
  }

  verify(user: AuthRequest): Observable<boolean> {
    return this.httpClient.post<boolean>(this.messageBaseUri + '/validate', user);
  }

  delete(): Observable<User> {
    return this.httpClient.delete<User>(this.messageBaseUri + '/delete', {headers: new HttpHeaders({
        'Content-Type': 'application/json', 'Access-Control-Allow-Origin':'*'})
    });
  }
}
