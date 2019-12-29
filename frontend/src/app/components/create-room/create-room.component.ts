import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Room } from 'src/app/dtos/room';
import { EventLocation } from 'src/app/dtos/event-location';
import { RoomService } from 'src/app/services/room.service';
import { LocationService } from 'src/app/services/location.service';
import { AuthService } from 'src/app/services/auth.service';
import { Seat } from 'src/app/dtos/seat';
import { Section } from 'src/app/dtos/section';
import { SeatService } from 'src/app/services/seat.service';
import { SectionService } from 'src/app/services/section.service';

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
  seatplanUpdated: Array<Seat>;
  configuring: boolean = false;
  toFewSeats: boolean = false;
  newSectionLetters: Array<string>;
  newSections: Array<Section>;


  constructor(
    private formbuilder: FormBuilder,
    private roomService: RoomService,
    private sectionService: SectionService,
    private seatService: SeatService,
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
    if (!this.seatplanUpdated) {
      return;
    }

    if (this.createRoomForm.valid) {
      const room: Room = new Room(
        null,
        this.createRoomForm.controls.name.value,
        this.createRoomForm.controls.location.value
      );
      this.seatSelection = false;
      this.createRoom(room);
      this.clearForm();
    } else {
      console.log('Invalid input');
    }
  }

  public createRoom(room: Room) {
    this.roomService.createRoom(room).subscribe(
      (retRoom: Room) => {
        this.room = retRoom;

        this.createSections(retRoom)
        
        this.sectionService.createSections(this.newSections).subscribe(newSections => {
          this.newSections = newSections;
          this.assignSectionsToSeats();
          console.log("Section ", this.newSections);
          console.log("Seats ", this.seatplanUpdated);
          

          this.seatService.createSeats(this.seatplanUpdated).subscribe((seats: Array<Seat>) => {
            this.seatplanUpdated = seats;
            this.success = true;
          },
            error => {
              this.defaultServiceErrorHandling(error);
            });
        });
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
    this.configuring = true;
    var rows: number;
    var seats: number;
    if (this.createSeatplanForm.valid) {
      rows = this.createSeatplanForm.controls.rowNumber.value;
      seats = this.createSeatplanForm.controls.seatsPerRow.value;
    } else {
      console.log('Invalid input');
    }

    if (rows < 1 || seats < 1 || typeof rows != 'number' || typeof seats != 'number') {
      this.toFewSeats = true;
      return;
    }
    this.toFewSeats = false;

    var seatplan: Array<Array<Seat>> = new Array<Array<Seat>>();

    for (let i = 0; i < rows; i++) {
      seatplan.push(new Array<Seat>());
      for (let j = 0; j < seats; j++) {
        seatplan[i].push(new Seat(null, j, String.fromCharCode(65 + i), new Section(null, null, false, null)));
      }
    }

    this.seatplan = seatplan;
    this.seatSelection = true;
  }

  setSeats(seatplan: Array<Array<Seat>>) {
    this.newSectionLetters = new Array<string>();
    this.seatplanUpdated = new Array<Seat>();
    for (let row of seatplan) {
      for (let seat of row) {
        this.seatplanUpdated.push(seat);
        if (!this.newSectionLetters.some(secLetter => secLetter == seat.section.letter)) {
          this.newSectionLetters.push(seat.section.letter);
        }
      }
    }
  }

  private clearForm() {
    this.createRoomForm.reset();
    this.createSeatplanForm.reset();
    this.configuring = false;
    this.submitted = false;
  }

  private createSections(room:Room):void{
    this.newSections = new Array<Section>();

    for (let letter of this.newSectionLetters) {
      this.newSections.push(new Section(null, letter, false, room));
    }
  }

  private assignSectionsToSeats(): void {
    for (let seat of this.seatplanUpdated) {
      for (let section of this.newSections) {
        if (section.letter == seat.section.letter) {
          seat.section = section;
        }
      }
    }
  }

}
