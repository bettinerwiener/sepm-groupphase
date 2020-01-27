import { Order } from './order';
import { EventPerformance } from './event-performance';
import { Seat } from './seat';

export class TicketDto {

    constructor(
        public id: number,
        public customerOrder: Order,
        public performance:EventPerformance,
        public seat: Seat,
        public status: string,
        public price: number,) {
            
            
    }
}
