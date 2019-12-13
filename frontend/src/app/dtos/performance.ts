import {Room} from './room';
import { GlobalEvent } from './global-event';


export class Performance {
    constructor(
      public id: number,
      public event: GlobalEvent,
      public room: Room,
      public date: Date) {
    }
  }
