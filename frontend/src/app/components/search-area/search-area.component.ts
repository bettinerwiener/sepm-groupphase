import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { EventLocation } from 'src/app/dtos/event-location';
import { Artist } from 'src/app/dtos/artist';
import { GlobalEvent } from 'src/app/dtos/global-event';
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
  @Output() searchedFilms = new EventEmitter<GlobalEvent[]>();
  @Output() searchedConcerts = new EventEmitter<GlobalEvent[]>();
  @Output() searchedTheatres = new EventEmitter<GlobalEvent[]>();
  locations: EventLocation[];
  errorMessage = 'There went something wrong while searching for events...';
  constructor(
    private searchService: SearchService,
    private locationService: LocationService) { }

  ngOnInit() {
    this.getAlllLocations();
    this.initGetEventForCategory();
  }

  initGetEventForCategory() {
    let category: string = this.category;
    if (this.category === 'films') {
        category = 'FILM';
      } else if (this.category === 'concerts') {
        category = 'CONCERT';
      } else if (this.category === 'theatres') {
        category = 'THEATER';
      }
    this.searchService.loadEvent(null, category, null, null, null, null, null, null).subscribe(
      (events: GlobalEvent[]) => {
        if ( category === 'THEATER') {
          this.searchedTheatres.emit(events);
        }
        if ( category === 'FILM') {
          this.searchedFilms.emit(events);
        }
        if (category === 'CONCERT') {
          this.searchedConcerts.emit(events);
        }
      },
      error => {
        console.log('No events found with this filter');
        this.searchedConcerts.emit(null);
        this.searchedEvents.emit(null);
        this.searchedTheatres.emit(null);
        this.searchedFilms.emit(null);
      }
    );
  }

  public getEvent(
    searchTerm: string,
    startDate: Date,
    endDate: Date,
    price: number,
    duration: number,
    location: EventLocation,
    artist: Artist) {
      let category: string = this.category;
      if (category === 'films') {
        category = 'FILM';
      } else if (category === 'concerts') {
        category = 'CONCERT';
      } else if (category === 'theatres') {
        category = 'THEATER';
      }
    this.searchService.loadEvent(searchTerm, category, startDate, endDate, price, duration, location, artist).subscribe(
      (events: GlobalEvent[]) => {
        this.searchedEvents.emit(events);
        if ( category === 'THEATER') {
          this.searchedTheatres.emit(events);
        }
        if ( category === 'FILM') {
          this.searchedFilms.emit(events);
        }
        if (category === 'CONCERT') {
          this.searchedConcerts.emit(events);
        }
      },
      error => {
        console.log('No events found with this filter');
        this.searchedConcerts.emit(null);
        this.searchedEvents.emit(null);
        this.searchedTheatres.emit(null);
        this.searchedFilms.emit(null);
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
