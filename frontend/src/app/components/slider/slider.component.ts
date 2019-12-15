import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/services/event.service';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { Observable } from 'rxjs';

@Component({
  selector: 'slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {

  constructor(private eventService: EventService) { }
  topTenEvents: GlobalEvent[];
  error: boolean = false;
  errorMessage: string = 'There was a problem loading the topTenEvents';

  ngOnInit() {
    this.loadTopTenEvents();
  }

  loadTopTenEvents() {
    this.eventService.getTopTenEvents().subscribe(
      (retTopTenEvents: GlobalEvent[]) => {
        this.topTenEvents = retTopTenEvents;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }



  swiperight(): void {
    const element = document.getElementById('slider');
    element.scroll({top: 0, left: 10000, behavior: 'smooth'});
  }

  swipeleft(): void {
    const element = document.getElementById('slider');
    element.scroll({top: 0, left: 0, behavior: 'smooth'});
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
