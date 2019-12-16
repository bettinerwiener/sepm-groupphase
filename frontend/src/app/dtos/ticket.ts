import { SeatplanObject } from './seatplan-object';
import { Time } from '@angular/common';
import { EventLocation } from './event-location';
import { Seat } from './seat';
import { EventObject } from './event-object';
import { EventPerformance } from './event-performance';

export class Ticket {
    public performance:EventPerformance;
    public selected:boolean = false;

    constructor(
        public id: number,
        public event: EventObject,
        public room: number,
        public seat: Seat,
        public status: string,
        public price: number,
        public location: string,
        public time: Time,) {
            
            
    }
}
