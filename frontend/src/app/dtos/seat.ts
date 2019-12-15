import { Section } from './section';

export class Seat {
    
    selected: boolean = false;
    sector:string;
    
        constructor(
            public id:number,
            public number:number,
            public row:string,
            public section:Section,
        ) { }
}
