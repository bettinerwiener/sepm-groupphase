
export class Event {
    constructor(
      public id: number,
      public title: string,
      public shortDescription: string,
      public contents: string,
      public category: string,
      public duration: number,
      public employee: number,
      public artist: string) {
    }
}

