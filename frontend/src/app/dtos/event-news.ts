import { Event } from "./event"
import { News } from "./news"
import { User } from './user'

export class EventNews {
    constructor(
        public event: Event,
        public news: News,
        public employee: User
    ) {}
}