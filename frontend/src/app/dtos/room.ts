import {EventLocation} from './event-location';


export class Room {
    constructor(
      public id: number,
      public name: String,
      public location: EventLocation) {
    }
  }
