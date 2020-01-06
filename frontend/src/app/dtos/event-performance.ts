import { Room } from './room';
import { GlobalEvent } from './global-event';
import { Ticket } from './ticket';
import { EventLocation } from './event-location';
import { SeatplanComponent } from '../components/seatplan/seatplan.component';

export class EventPerformance {
    public seatSelection: boolean = false;
    public seatPlan: SeatplanComponent;
    public tickets: Array<Array<Ticket>>;

    constructor(
        public id: number,
        public event: GlobalEvent,
        public room: Room,
        public date: Date,
    ) { }
}
