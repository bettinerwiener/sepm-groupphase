import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Globals } from '../global/globals';
import { Observable } from 'rxjs';
<<<<<<< HEAD
=======
import { EventNews } from '../dtos/event-news';
>>>>>>> 6cc47a352a74e5cacb677d653e0e24df5fde6ea4
import { News } from '../dtos/news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  newsBaseUri: string = this.globals.backendUri + '/news';

<<<<<<< HEAD
  constructor(private httpClient: HttpClient, private globals: Globals) { }

  getAllNews(): Observable<News[]> {
    console.log('Get all news');
    return this.httpClient.get<News[]>(this.newsBaseUri);
  }

=======
    constructor(private httpClient: HttpClient, private globals: Globals) { }


    getNewsById(id: number): Observable<News> {
        console.log("Get event news by id " + id);
        return this.httpClient.get<News>(this.newsBaseUri + '/' + id);
    }

    updateNews(news: News): Observable<News> {
      console.log("Updating news entry with id " + news.id);
      return this.httpClient.put<News>(this.newsBaseUri, news);
    }

    updateImage(formData: FormData, id: Number): Observable<Boolean> {
        console.log("Sending image separately");
        return this.httpClient.post<Boolean>(this.newsBaseUri + '/' + id, formData);
    }

    getImage(id: Number) {
        console.log("Getting image for news entry with id " + id);
        return this.httpClient.get(this.newsBaseUri + '/' + id + '/image', {observe: 'response', responseType: 'blob'});
    }
>>>>>>> 6cc47a352a74e5cacb677d653e0e24df5fde6ea4
}
