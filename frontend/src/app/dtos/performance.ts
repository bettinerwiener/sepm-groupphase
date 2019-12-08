import {Event} from './event';
import {Room} from './room';


export class Performance {
    constructor(
      public id: number,
      public event: Event,
      public room: Room,
      public date: Date,) {
    }
  }
  