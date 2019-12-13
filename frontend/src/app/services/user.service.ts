import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';
import {HttpClient} from '@angular/common/http';
import {User} from '../dtos/user';

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


}
