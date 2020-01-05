import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { CustomerNews } from '../dtos/customer-news';


@Injectable({
  providedIn: 'root'
})
export class CustomerNewsService {

  customerNewsBaseUri: string = this.globals.backendUri + 'customernews';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getCustomerNews(): Observable<CustomerNews[]> {
    console.log('Get all customer news');
    return this.httpClient.get<CustomerNews[]>(this.customerNewsBaseUri);
  }

  setCustomerNewsToRead(customerNews: CustomerNews): Observable<CustomerNews> {
    console.log('Set customer news to read');
    return this.httpClient.put<CustomerNews>(this.customerNewsBaseUri, customerNews);

  }
}
