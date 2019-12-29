import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Room } from 'src/app/dtos/room';
import { EventLocation } from 'src/app/dtos/event-location';
import { RoomService } from 'src/app/services/room.service';
import { LocationService } from 'src/app/services/location.service';
import { AuthService } from 'src/app/services/auth.service';
import { Ticket } from 'src/app/dtos/ticket';
import { Seat } from 'src/app/dtos/seat';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Section } from 'src/app/dtos/section';

@Component({
  selector: 'app-create-room',
  templateUrl: './create-room.component.html',
  styleUrls: ['./create-room.component.scss']
})
export class CreateRoomComponent implements OnInit {

  createRoomForm: FormGroup;
  createSeatplanForm: FormGroup;
  room: Room;
  error: boolean = false;
  success: boolean = false;
  locations: EventLocation[];
  submitted: boolean = false;
  errorMessage: string = 'There was a problem creating this room.';
  seatSelection: boolean = false;
  seatplan: Array<Array<Seat>>;


  constructor(
    private formbuilder: FormBuilder,
    private roomService: RoomService,
    private authService: AuthService,
    private locationService: LocationService) {
    this.createRoomForm = this.formbuilder.group({
      name: ['', Validators.required],
      location: [Validators.required]
    });
    this.createSeatplanForm = this.formbuilder.group({
      rowNumber: [Validators.required],
      seatsPerRow: [Validators.required]
    });
  }

  ngOnInit() {
    this.getAllLocations();
  }

  isAdmin(): boolean {
    return this.authService.getUserRole() === 'ADMIN';
  }

  addRoom() {
    this.submitted = true;
    if (this.createRoomForm.valid) {
      const room: Room = new Room(
        null,
        this.createRoomForm.controls.name.value,
        this.createRoomForm.controls.location.value
      );
      this.createRoom(room);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createRoom(room: Room) {
    this.roomService.createRoom(room).subscribe(
      (retRoom: Room) => {
        this.room = room;
        this.success = true;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private getAllLocations() {
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

  vanishError() {
    this.error = false;
  }

  vanishSuccess() {
    this.success = false;
  }

  configure() {
    var rows:number;
    var seats:number;
    if (this.createSeatplanForm.valid) {
        rows = this.createSeatplanForm.controls.rowNumber.value;
        seats = this.createSeatplanForm.controls.seatsPerRow.value;
    } else {
      console.log('Invalid input');
    }

    if(typeof rows != 'number' || typeof seats != 'number'){
      console.log('Error: Unset parameters.');
      return
    }

    if(rows < 1 || seats < 1){
      console.log('To few rows / seats.');
      return;
    }

    var seatplan:Array<Array<Seat>> = new Array<Array<Seat>>();

    for(let i = 0; i < rows; i++){
      seatplan.push(new Array<Seat>());
      for(let j = 0; j < seats; j++){
        seatplan[i].push(new Seat(null,j,String.fromCharCode(65 + i), new Section(null, null, false, null)));
      }
    }

    this.seatplan = seatplan;
    this.seatSelection = true;
  }

  private clearForm() {
    this.createRoomForm.reset();
    this.submitted = false;
  }

}
