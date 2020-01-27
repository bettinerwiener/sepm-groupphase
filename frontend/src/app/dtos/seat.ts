import { Section } from './section';

export class Seat {
    
    selected: boolean = false;
    sector:string;
    
        constructor(
            public id:number,
            public seatNumber:number,
            public rowLetter:string,
            public section:Section,
        ) { }
}
