import { Component, OnInit } from '@angular/core';
import {EventService} from '../../services/event.service';
import {Event} from '../../dtos/event';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.scss']
})
export class CreateEventComponent implements OnInit {
  createEventForm: FormGroup;
  event: Event;
  error: boolean = false;

  // After first submission attempt, form validation will start
  submitted: boolean = false;
  errorMessage: string = 'This is a useless errormessage';
  constructor(private formbuilder: FormBuilder, private eventService: EventService) {
    this.createEventForm = this.formbuilder.group({
      title:        ['', Validators.required],
      description:  ['', Validators.required],
      date:         [Validators.required],
      duration:     [Validators.required],
      location:     ['', Validators.required],
      category:     [Validators.required],
    });
   }

  ngOnInit() {
  }

  public createEvent() {
    this.submitted = true;
    console.log(this.createEventForm.controls.title.value);
    if (this.createEventForm.valid) {
      console.log(this.createEventForm.controls.duration.value);
      this.eventService.createEvent(this.createEventForm.controls.title.value,
        this.createEventForm.controls.description.value,
        this.createEventForm.controls.date.value,
        this.createEventForm.controls.duration.value,
        this.createEventForm.controls.location.value,
        this.createEventForm.controls.category.value).subscribe(
        (event: Event) => {
          this.event = event;
        },
        error => {
          console.log(this.createEventForm.controls.title.value);
          this.defaultServiceErrorHandling(error);
        }
      );
    } else {
      console.log('Invalid form');
    }
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

  vanishError() {
    this.error = false;
  }




}
