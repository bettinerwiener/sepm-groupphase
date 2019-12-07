import { RouterModule } from '@angular/router';
import { Room } from './room';

export class EventPerformance {
    constructor(
        public event: Event,
        public room: Room,
        public date: Date,
    ) {}
}
