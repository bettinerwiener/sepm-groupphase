import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { Performance } from '../dtos/performance';

@Injectable({
  providedIn: 'root'
})
export class PerformanceService {

  private performanceBaseUri: string = this.globals.backendUri + '/performance';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getPerformance(): Observable<Performance[]> {
    console.log('Get all performances');
    return this.httpClient.get<Performance[]>(this.performanceBaseUri);
  }

  createPerformance(performance: Performance): Observable<Performance> {
    console.log('Post performance');
    return this.httpClient.post<Performance>(this.performanceBaseUri, performance);
  }
}
