
import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Globals} from '../global/globals';
import {Order} from '../dtos/order';
@Injectable({
  providedIn: 'root'
})
export class PdfService {

  private pdfBaseUri: string = this.globals.backendUri + '/pdf';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Loads all orders of logged in user from the backend
   */
  getTicket(id: number): Observable<Blob> {
    return this.httpClient.get(this.pdfBaseUri + "/ticket/" + id, {responseType: 'blob'});
  }
}
