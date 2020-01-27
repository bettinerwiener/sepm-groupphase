import {Ticket} from './ticket';

export class Order {
  constructor(
    public id: number,
    public userId: number,
    public tickets: Ticket[],
    public status: string) {
  }
}
