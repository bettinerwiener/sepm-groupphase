import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'event-list-item',
  templateUrl: './event-list-item.component.html',
  styleUrls: ['./event-list-item.component.scss'],
  providers: [ɵROUTER_PROVIDERS]
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

  constructor(private router: Router, private eventService: EventService) { }

  ngOnInit() {
  }

  getId(id: number){
    this.router.navigate(['/event/', id]).then(
      () => window.location.reload());
  }

  


}
