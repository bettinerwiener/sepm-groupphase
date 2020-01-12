export class News {
    constructor(
        public id: number,
        public title: string,
        public shortDescription: string,
        public entry: string,
        public image: Blob,
        public publishedAt: Date
    ) {}
}