
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';
import {Order} from '../dtos/order';
import { Ticket } from '../dtos/ticket';

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

  getOrdersByEmail(email: string): Observable<Order[]> {
    return this.httpClient.get<Order[]>(this.ordersBaseUri + "/" + email);
  }

  cancelReservation(toCancel: Number[]): Observable<Ticket[]> {
    console.log("cancelReservation service:")
    console.log(toCancel);
    return this.httpClient.put<Ticket[]>(this.ordersBaseUri + "/cancel", toCancel);
  }

  cancelPurchase(toCancel: Number[]): Observable<Blob> {
    console.log("cancelPurchase service:")
    return this.httpClient.put(this.ordersBaseUri + "/cancelpurchase", toCancel, {responseType: 'blob'});
  }

  purchaseTickets(toBuy: Number[]): Observable<Ticket[]> {
    console.log("purchaseTickets service:");
    return this.httpClient.post<Ticket[]>(this.ordersBaseUri + "/buyreserved", toBuy);

  }
  purchaseTicketsAsAdmin(toBuy: Number[]): Observable<Ticket[]> {
    console.log("purchaseTickets service:");
    return this.httpClient.post<Ticket[]>(this.ordersBaseUri + "/buyasadmin", toBuy);

  }

}
