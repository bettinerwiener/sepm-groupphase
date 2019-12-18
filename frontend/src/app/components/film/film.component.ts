import { Component, Input, OnInit } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-films',
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {

  films: GlobalEvent[];

  getFilms(films: GlobalEvent[]): void {
    this.films = films;
  }

  constructor() { }

  ngOnInit() {
  }

}
