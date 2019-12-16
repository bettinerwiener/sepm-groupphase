import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public authService: AuthService) { }

  events: GlobalEvent[];

  ngOnInit() {
  }

  getEvents(events: GlobalEvent[]): void {
    this.events = events;
  }

}
