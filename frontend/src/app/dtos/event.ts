export class Event {
    constructor(
      public title: string,
      public description: string,
      public date: Date,
      public duration: number,
      public location: string,
      public category: string,
      // public employee: number
    ) {}
  }
