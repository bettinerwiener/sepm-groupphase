import { Component, Input, OnInit } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.scss']
})
export class MoviesComponent implements OnInit {

  movies: GlobalEvent[];

  getMovies(movies: GlobalEvent[]): void {
    this.movies = movies;
  }

  constructor() { }

  ngOnInit() {
  }

}
