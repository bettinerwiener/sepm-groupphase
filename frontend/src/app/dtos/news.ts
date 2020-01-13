export class News {
    public abstract:string;

    constructor(
        public id: number,
        public title: string,
        public shortDescription: string,
        public entry: string,
        public image: string,
        public publishedAt: Date
    ) {}
}