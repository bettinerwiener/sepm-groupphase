import { Event } from './event'
import { News } from './news'
import { User } from './user';

export class CustomerNews {
    constructor(
        public read: boolean,
        public user: User,
        public news: News,
    ) {}
}
