import { Time } from '@angular/common';
import { SeatplanObject } from './seatplan-object';
import { Ticket } from './ticket';
import { EventPerformance } from './event-performance';

export class EventObject {
    
    public performances: Array<EventPerformance>;

    constructor(
        public tickets: Array<Ticket>,
        public title: string,
        public abstract: string,
        public contents: string,
        public category: string,
        public duration: number,
        public employee: number,
        public firstPerformance: Time,
        public lastPerformance: Time,
    ) { }
}
