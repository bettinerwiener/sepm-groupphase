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
}
