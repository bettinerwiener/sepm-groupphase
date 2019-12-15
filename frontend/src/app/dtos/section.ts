import { Room } from './room';

export class Section {
  constructor(
    public id: number,
    public letter: string,
    public seatsSelectable: false,
    public room: Room, ) {
  }
}
