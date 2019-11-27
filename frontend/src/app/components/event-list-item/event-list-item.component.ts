import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'event-list-item',
  templateUrl: './event-list-item.component.html',
  styleUrls: ['./event-list-item.component.scss']
})
export class EventListItemComponent implements OnInit {

  faCalendarDay = faCalendarDay
  faMapMarkerAlt = faMapMarkerAlt

  @Input() title: string;
  @Input() description: string;
  @Input() date: Date;
  @Input() location: string;
  @Input() price: number;





  constructor() { }

  ngOnInit() {
  }

}
