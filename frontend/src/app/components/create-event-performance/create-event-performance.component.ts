import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventPerformanceService } from 'src/app/services/event-performance.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
<<<<<<< HEAD
=======
import { RoomService } from 'src/app/services/room.service';
import { Room } from 'src/app/dtos/room';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { EventService } from 'src/app/services/event.service';
import { stringify } from 'querystring';
>>>>>>> tmp

@Component({
  selector: 'create-event-performance',
  templateUrl: './create-event-performance.component.html',
  styleUrls: ['./create-event-performance.component.scss']
})
export class CreateEventPerformanceComponent implements OnInit {

  createEventPerformanceForm: FormGroup;
  eventPerformance: EventPerformance;
  error: boolean = false;
  private eventPerformances: EventPerformance[];
  submitted: boolean = false;
  errorMessage: string = 'This is another useless errormessage';

  constructor(
    private formbuilder: FormBuilder,
    private eventPerformanceService: EventPerformanceService,
    private authService: AuthService) {
    this.createEventPerformanceForm = this.formbuilder.group({
      event: [Validators.required],
      room: [Validators.required],
      date: [Validators.required],
      time: [Validators.required]
    });
  }


  ngOnInit() {
  }

  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  addEventPerformance() {
    this.submitted = true;
    if (this.createEventPerformanceForm.valid) {
      console.log(this.createEventPerformanceForm.controls.time.value);
      console.log(this.createEventPerformanceForm.controls.date.value);

      const formattedDate = new Date(
        this.createEventPerformanceForm.controls.date.value + ' ' +
        this.createEventPerformanceForm.controls.time.value
      );

      console.log(formattedDate);

      const eventPerformance: EventPerformance = new EventPerformance(
        0,
        this.createEventPerformanceForm.controls.event.value,
        null,
        this.createEventPerformanceForm.controls.room.value,
        formattedDate
      );



      this.createEventPerformance(eventPerformance);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createEventPerformance(eventPerformance: EventPerformance) {
    this.eventPerformanceService.createEventPerformance(eventPerformance).subscribe(
<<<<<<< HEAD
      () => {
      },
=======
      (retPerformance: EventPerformance) => {
        this.eventPerformance = retPerformance;
       },
>>>>>>> tmp
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }



  private loadEventPerformance() {
    this.eventPerformanceService.getEventPerformance().subscribe(
      (eventPerformances: EventPerformance[]) => {
        this.eventPerformances = eventPerformances;
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

  vanishError() {
    this.error = false;
  }

  private clearForm() {
    this.createEventPerformanceForm.reset();
    this.submitted = false;
  }

}
