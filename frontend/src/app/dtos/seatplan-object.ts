import { Seat } from './seat';

export class SeatplanObject {

    numberOfRows:number;
    numberOfSeatsPerRow:number;
    numberOfSectors:number = 5;
    rows:Array<Array<Seat>> = Array<Array<Seat>>();

    constructor(numberOfRows:number, numberOfSeatsPerRow:number, rows:Array<Array<Seat>>){
        this.numberOfRows = numberOfRows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
        this.rows = rows;
    }
}