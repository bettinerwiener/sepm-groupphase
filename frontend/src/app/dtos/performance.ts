import { RouterModule } from '@angular/router';
import { Room } from './room';

export class Performance {
    constructor(
        public event: Event,
        room: Room,
        date: Date,
    ) {}
}
