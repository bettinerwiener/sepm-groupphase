import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { EventLocation } from 'src/app/dtos/event-location';
import { Artist } from 'src/app/dtos/artist';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { Subject } from 'rxjs';
import { LocationService } from 'src/app/services/location.service';
import {Time} from '@angular/common';
import {start} from 'repl';
import { ArtistService } from 'src/app/services/artist.service';

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
  artists: Artist[];
  errorMessage = 'There went something wrong while searching for events...';
  constructor(
    private searchService: SearchService,
    private locationService: LocationService,
    private artistService: ArtistService) { }

  ngOnInit() {
    this.getAllCities();
    this.getAllArtists();
    this.initGetEventForCategory();
  }

  initGetEventForCategory() {
    let category: string = null;
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
        } else if ( category === 'FILM') {
          this.searchedFilms.emit(events);
        } else if (category === 'CONCERT') {
          this.searchedConcerts.emit(events);
        }
      },
      error => {
        console.log('No events matching this criteria');
          this.searchedEvents.emit(null);
          this.searchedFilms.emit(null);
          this.searchedConcerts.emit(null);
          this.searchedTheatres.emit(null);
        }
    );
  }

  public getEvent(
    searchTerm: string,
    startDate: string,
    startTime: string,
    endDate: string,
    endTime: string,
    price: number,
    duration: number,
    location: string,
    artist: string) {
      let category: string = null;
      let start_date: Date;
      let end_date: Date;
      if (this.category === 'films') {
        category = 'FILM';
      } else if (this.category === 'concerts') {
        category = 'CONCERT';
      } else if (this.category === 'theatres') {
        category = 'THEATER';
      }
      if (startDate != null && startDate !== '') {
        const dateString: string[] = startDate.split('-');
        if (startTime != null) {
          const timeString: string[] = startTime.split(':');
          start_date = new Date(Number(dateString[0]), Number(dateString[1]) - 1, Number(dateString[2]),
            Number(timeString[0]) + 1, Number(timeString[1]), 0);
        } else {
          start_date = new Date(Number(dateString[0]), Number(dateString[1]) - 1, Number(dateString[2]),
            0, 0, 0);
        }
        console.log(startDate + ', ' + startTime);
      }
    if (endDate != null) {
      const dateString: string[] = endDate.split('-');
      if (endTime != null) {
        const timeString: string[] = endTime.split(':');
        end_date = new Date(Number(dateString[0]), Number(dateString[1]) - 1, Number(dateString[2]),
          Number(timeString[0]) + 1, Number(timeString[1]), 0);
      } else {
        end_date = new Date(Number(dateString[0]), Number(dateString[1]) - 1, Number(dateString[2]),
          0, 0, 0);
      }
      console.log(startDate + ', ' + startTime);
    }
      console.log('The city of the location is ' + location);
    this.searchService.loadEvent(searchTerm, category, start_date, end_date, price, duration, location, artist).subscribe(
      (events: GlobalEvent[]) => {
        console.log(events);
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
        console.log('No events matching this criteria');
        this.searchedEvents.emit(null);
        this.searchedFilms.emit(null);
        this.searchedConcerts.emit(null);
        this.searchedTheatres.emit(null);
      }
    );
  }

  private getAllCities() {
    this.locationService.getCities().subscribe(
      (locations: EventLocation[]) => {
        this.locations = locations;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private getAllArtists() {
    this.artistService.getArtists().subscribe(
      (artists: Artist[]) => {
        this.artists = artists;
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
