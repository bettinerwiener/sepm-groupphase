import { Component, OnInit, Input } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-theatre',
  templateUrl: './theatre.component.html',
  styleUrls: ['./theatre.component.scss']
})
export class TheatreComponent implements OnInit {

  @Input() theatres: GlobalEvent[];

  constructor() { }

  ngOnInit() {
  }

}
