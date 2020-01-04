import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
import { News } from '../dtos/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  newsBaseUri: string = this.globals.backendUri + '/news';

  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getAllNews(): Observable<News[]> {
    console.log('Get all news');
    return this.httpClient.get<News[]>(this.newsBaseUri);
  }

}
