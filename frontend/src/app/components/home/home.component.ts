import {Component, OnInit, Input} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  constructor(public authService: AuthService, private ticketService: TicketService) { }

  events: GlobalEvent[];
  @Input() firstPerformanceDate: Date;
  @Input() lastPerformanceDate: Date;
  error: boolean = false;
  errorMessage = 'This is a useless Errormessage';

  ngOnInit() {
  }

  getEvents(events: GlobalEvent[]): void {
    this.events = events;
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }



}
