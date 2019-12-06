import { SeatplanObject } from './seatplan-object';
import { Time } from '@angular/common';

export class Ticket {


    constructor(
        public seatplan:SeatplanObject,
        public location:string,
        public hall:string,
        public time:Time){
            
        }
}
