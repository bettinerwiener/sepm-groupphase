import {Seat} from './seat';
import {Performance} from './performance';


export class Ticket {
    constructor(
      public id: number,
      public performance: Performance,
      public seat: Seat,
      public price: number,
      public status: string) {
    }
}
