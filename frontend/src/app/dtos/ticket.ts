import { SeatplanObject } from './seatplan-object';
import { Time } from '@angular/common';
import { EventLocation } from './event-location';
import { Seat } from './seat';
import { EventObject } from './event-object';
import { EventPerformance } from './event-performance';
import { TicketDto } from './ticket-dto';
import { Order } from './order';

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
        public time: Time,) { }

    toDto():TicketDto {
        return new TicketDto(this.id, null, this.performance, this.seat, this.status, this.price);
    }
}
