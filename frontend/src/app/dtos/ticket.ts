<<<<<<< HEAD
import {Seat} from './seat';
import {Performance} from './performance';


export class Ticket {
    constructor(
      public id: number,
      public performance: Performance,
      public seat: Seat,
      public price: number,
      public status: string) {
    }
=======
import { SeatplanObject } from './seatplan-object';
import { Time } from '@angular/common';
import { EventLocation } from './event-location';

export class Ticket {
    constructor(
        public seatplan: SeatplanObject,
        public location: EventLocation,
        public hall: string,
        public time: Time) {
        }
>>>>>>> createEvent
}
