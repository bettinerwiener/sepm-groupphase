import {Room} from './room';

export class Section {
    constructor(
      public id: number,
      public seatsSelectable: false,
      public room: Room,) {
    }
  }
  