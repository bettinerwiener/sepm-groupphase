export class Seat {

    row: number;
    seatnumber: number;
    selected: boolean;
    taken:boolean;
    sector:number;

        constructor(row: number, seatnumber:number, sector:number) {
            this.row = row;
            this.seatnumber = seatnumber;
            this.sector = sector;
            this.selected = false;
            this.taken = false;
        }
}
