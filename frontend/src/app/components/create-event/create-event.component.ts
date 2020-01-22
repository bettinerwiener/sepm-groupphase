import { Component, OnInit } from '@angular/core';
import {EventService} from '../../services/event.service';
import { GlobalEvent } from '../../dtos/global-event';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import { Room } from 'src/app/dtos/room';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {
  createEventForm: FormGroup;
  event: GlobalEvent;
  error: boolean = false;
  success: boolean = false;
  formData: FormData;
  private events: GlobalEvent[];
  private rooms: Room[];

  // After first submission attempt, form validation will start
  submitted: boolean = false;
  errorMessage: string = 'This is a useless errormessage';
  constructor(private formbuilder: FormBuilder,
    private eventService: EventService,
    private authService: AuthService) {
    this.createEventForm = this.formbuilder.group({
      title:        ['', Validators.required],
      category:     [Validators.required],
      shortDescription:  ['', Validators.required],
      contents:         ['', Validators.required],
      duration:     [Validators.required]
    });
  }

  ngOnInit() {
    this.formData = new FormData();
  }

   /**
   * Returns true if the authenticated user is an admin
   */
  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  addEvent() {
    this.submitted = true;
    if (this.createEventForm.valid) {
      const event: GlobalEvent = new GlobalEvent(
        null,
        this.createEventForm.controls.title.value,
        this.createEventForm.controls.category.value,
        this.createEventForm.controls.shortDescription.value,
        this.createEventForm.controls.contents.value,
        this.createEventForm.controls.duration.value
      );
      console.log('Event: ' + event.category);
      this.createEvent(event);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public sendImage(formData: FormData, id: Number) {
    this.eventService.sendImage(formData, id).subscribe(
      success => {
        console.log('done');
        this.success = true;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  public createEvent(event: GlobalEvent) {
    this.eventService.createEvent(event).subscribe(
      (retEvent: GlobalEvent) => {
        this.event = retEvent;
        this.success = true;
        this.sendImage(this.formData, this.event.id);
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private loadCreatedEvent() {
    this.eventService.getCreatedEvent().subscribe(
      (event: GlobalEvent) => {
        this.event = event;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private loadEvent() {
    this.eventService.getEvent().subscribe(
      (event: GlobalEvent[]) => {
        this.events = event;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
      this.formData.append('image', event.target.files[0]);
    }
  }

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
    this.createEventForm.reset();
    this.submitted = false;
  }




}
