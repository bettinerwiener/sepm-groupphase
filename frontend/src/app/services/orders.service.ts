
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';
import {Order} from '../dtos/order';
@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  private ordersBaseUri: string = this.globals.backendUri + '/orders';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Loads all orders of logged in user from the backend
   */
  getOrders(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.ordersBaseUri);
  }
}
