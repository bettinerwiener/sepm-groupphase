import { EventLocation } from './event-location';

export class Room {
    constructor(
        public name: string,
        public eventLocation: EventLocation
    ) {}
}
