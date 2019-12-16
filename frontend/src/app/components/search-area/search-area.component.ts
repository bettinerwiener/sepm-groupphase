import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { SearchService } from 'src/app/services/search.service';
import { EventLocation } from 'src/app/dtos/event-location';
import { Artist } from 'src/app/dtos/artist';
import { ConcertComponent } from '../concert/concert.component';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { MoviesComponent } from '../movies/movies.component';
import { Subject } from 'rxjs';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'search-area',
  templateUrl: './search-area.component.html',
  styleUrls: ['./search-area.component.scss']
})
export class SearchAreaComponent implements OnInit {

  @Input() category: string;
  error = false;
  @Output() searchedEvents = new EventEmitter<GlobalEvent[]>();
  @Output() searchedMovies = new EventEmitter<GlobalEvent[]>();
  @Output() searchedConcerts = new EventEmitter<GlobalEvent[]>();
  @Output() searchedCabarets = new EventEmitter<GlobalEvent[]>();
  @Output() searchedTheatres = new EventEmitter<GlobalEvent[]>();
  locations: EventLocation[];
  errorMessage = 'This is a useless Errormessage';
  constructor(
    private searchService: SearchService,
    private locationService: LocationService) { }

  ngOnInit() {
    this.getAlllLocations();
  }

  public getEvent(
    searchTerm: string,
    startDate: Date,
    endDate: Date,
    price: number,
    duration: number,
    location: EventLocation,
    artist: Artist) {
      console.log(searchTerm);

    this.searchService.loadEvent(searchTerm, this.category, startDate, endDate, price, duration, location, artist).subscribe(
      (events: GlobalEvent[]) => {
        this.searchedEvents.emit(events);
        if ( this.category === 'theatres') {
          this.searchedTheatres.emit(events);
        }
        if ( this.category === 'movies') {
          this.searchedMovies.emit(events);
        }
        if (this.category === 'cabarets') {
          this.searchedCabarets.emit(events);
        }
        if (this.category === 'concerts') {
          this.searchedConcerts.emit(events);
        }
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private getAlllLocations() {
    this.locationService.getLocation().subscribe(
      (locations: EventLocation[]) => {
        this.locations = locations;
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
