import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EventPerformanceService } from 'src/app/services/event-performance.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { RoomService } from 'src/app/services/room.service';
import { Room } from 'src/app/dtos/room';

@Component({
  // tslint:disable-next-line: component-selector
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
    this.roomService.getRoom().subscribe(
      (rooms: Room[]) => {
        this.rooms = rooms;
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
