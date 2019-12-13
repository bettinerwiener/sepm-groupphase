import { RouterModule } from '@angular/router';
import { Room } from './room';
import { GlobalEvent } from './global-event';

export class EventPerformance {
    constructor(
        public event: GlobalEvent,
        public room: Room,
        public date: Date,
    ) {}
}
