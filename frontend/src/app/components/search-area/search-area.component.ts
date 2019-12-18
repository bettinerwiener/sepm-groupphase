import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { EventLocation } from 'src/app/dtos/event-location';
import { Artist } from 'src/app/dtos/artist';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { Subject } from 'rxjs';

@Component({
  selector: 'search-area',
  templateUrl: './search-area.component.html',
  styleUrls: ['./search-area.component.scss']
})
export class SearchAreaComponent implements OnInit {

  @Input() category: string;
  error = false;
  @Output() searchedEvents = new EventEmitter<GlobalEvent[]>();
  @Output() searchedFilms = new EventEmitter<GlobalEvent[]>();
  @Output() searchedConcerts = new EventEmitter<GlobalEvent[]>();
  @Output() searchedTheatres = new EventEmitter<GlobalEvent[]>();
  errorMessage = 'This is a useless Errormessage';
  constructor(
    private searchService: SearchService) { }

  ngOnInit() {
  }

  public getEvent(
    searchTerm: string,
    startDate: Date,
    endDate: Date,
    price: number,
    duration: number,
    eventLocation: EventLocation,
    artist: Artist) {
      console.log(searchTerm);
      if (this.category === 'films') {
        this.category = 'FILM';
      } else if (this.category === 'concerts') {
        this.category = 'CONCERT';
      } else if (this.category === 'theatres') {
        this.category = 'THEATER';
      }
    this.searchService.loadEvent(searchTerm, this.category, startDate, endDate, price, duration, eventLocation, artist).subscribe(
      (events: GlobalEvent[]) => {
        this.searchedEvents.emit(events);
        if ( this.category === 'THEATER') {
          this.searchedTheatres.emit(events);
        }
        if ( this.category === 'FILM') {
          this.searchedFilms.emit(events);
        }
        if (this.category === 'CONCERT') {
          this.searchedConcerts.emit(events);
        }
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
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
