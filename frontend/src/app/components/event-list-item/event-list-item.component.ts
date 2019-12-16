import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { Globals } from 'src/app/global/globals';

@Component({
  selector: 'event-list-item',
  templateUrl: './event-list-item.component.html',
  styleUrls: ['./event-list-item.component.scss']
})
export class EventListItemComponent implements OnInit {

  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;

  @Input() id: number;
  @Input() title: string;
  @Input() description: string;
  @Input() date: Date;
  @Input() location: string;
  @Input() price: number;

  eventUrl: string = this.globals.backendUri + '/events';

  constructor(private router: Router, private globals: Globals) { }

  ngOnInit() {
  }

  eventId(id: number) {
    this.router.navigate([this.eventUrl, id]);
  }

}
