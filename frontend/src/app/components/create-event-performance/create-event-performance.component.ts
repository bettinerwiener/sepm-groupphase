import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventPerformanceService } from 'src/app/services/event-performance.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { RoomService } from 'src/app/services/room.service';
import { Room } from 'src/app/dtos/room';

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
  private rooms: Room[];
  submitted: boolean = false;
  errorMessage: string = 'This is another useless errormessage';

  constructor(
    private formbuilder: FormBuilder,
    private eventPerformanceService: EventPerformanceService,
    private authService: AuthService,
    private roomService: RoomService) {
    this.createEventPerformanceForm = this.formbuilder.group({
      event: [Validators.required],
      room: [Validators.required],
      date: [Validators.required],
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
      const eventPerformance: EventPerformance = new EventPerformance(
        this.createEventPerformanceForm.controls.event.value,
        this.createEventPerformanceForm.controls.room.value,
        this.createEventPerformanceForm.controls.date.value
      );
      this.createEventPerformance(eventPerformance);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createEventPerformance(eventPerformance: EventPerformance) {
    this.eventPerformanceService.createEventPerformance(eventPerformance).subscribe(
      () => {
      },
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

  private getAllRooms() {
    let elm = document.getElementById('room');
    let df = document.createDocumentFragment();
    this.roomService.getRoom().subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
        for (let i = 0; i < rooms.length; i++){
          let option = document.createElement('option');
          option.value = rooms[i];
          option.appendChild(document.createTextNode('option #' + i));
          df.appendChild(option);
        }
        elm.appendChild(df);
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  /**
   * 
   * (function() { // don't leak
    var elm = document.getElementById('foo'), // get the select
        df = document.createDocumentFragment(); // create a document fragment to hold the options while we create them
    for (var i = 1; i <= 42; i++) { // loop, i like 42.
        var option = document.createElement('option'); // create the option element
        option.value = i; // set the value property
        option.appendChild(document.createTextNode("option #" + i)); // set the textContent in a safe way.
        df.appendChild(option); // append the option to the document fragment
    }
    elm.appendChild(df); // append the document fragment to the DOM. this is the better way rather than setting innerHTML a bunch of times (or even once with a long string)
}());
   */

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
