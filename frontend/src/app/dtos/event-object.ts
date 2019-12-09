import { Time } from '@angular/common';
import { SeatplanObject } from './seatplan-object';

export class EventObject {

    constructor(
        public location: string,
        public hall: string,
        public time: Time,
        public seatplan: SeatplanObject
    ) {}
}
