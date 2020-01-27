import { Component, OnInit } from '@angular/core';
import {EventService} from '../../services/event.service';
import { GlobalEvent } from '../../dtos/global-event';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import { Room } from 'src/app/dtos/room';
import { EventLocation } from 'src/app/dtos/event-location';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-create-location',
  templateUrl: './create-location.component.html',
  styleUrls: ['./create-location.component.scss']
})
export class CreateLocationComponent implements OnInit {
  createLocationForm: FormGroup;
  error: boolean = false;
  success: boolean = false;
  location: EventLocation;

  // After first submission attempt, form validation will start
  submitted: boolean = false;
  errorMessage: string = 'This is a useless errormessage';
  constructor(private formbuilder: FormBuilder,
    private locationService: LocationService,
    private authService: AuthService) {
    this.createLocationForm = this.formbuilder.group({
      name:        ['', Validators.required],
      street:     ['', Validators.required],
      city:  ['', Validators.required],
      postalCode:         [Validators.required],
    });
  }

  ngOnInit() {
  }

   /**
   * Returns true if the authenticated user is an admin
   */
  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  addLocation() {
    this.submitted = true;
    if (this.createLocationForm.valid) {
      const location: EventLocation = new EventLocation(
        null,
        this.createLocationForm.controls.name.value,
        this.createLocationForm.controls.street.value,
        this.createLocationForm.controls.city.value,
        this.createLocationForm.controls.postalCode.value
      );
      this.createLocation(location);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createLocation(location: EventLocation) {
    this.locationService.createLocation(location).subscribe(
      (retLocation: EventLocation) => {
        this.location = retLocation;
        this.success = true;      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  // private loadCreatedEvent() {
  //   this.eventService.getCreatedEvent().subscribe(
  //     (event: GlobalEvent) => {
  //       this.event = event;
  //     },
  //     error => {
  //       this.defaultServiceErrorHandling(error);
  //     }
  //   );
  // }

  // private loadEvent() {
  //   this.eventService.getEvent().subscribe(
  //     (event: GlobalEvent[]) => {
  //       this.events = event;
  //     },
  //     error => {
  //       this.defaultServiceErrorHandling(error);
  //     }
  //   );
  // }

  private defaultServiceErrorHandling(error: any) {
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

  vanishError() {
    this.error = false;
  }

  vanishSuccess() {
    this.success = false;
  }

  private clearForm() {
    this.createLocationForm.reset();
    this.submitted = false;
  }




}
