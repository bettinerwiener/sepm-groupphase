export class GlobalEvent {
  // ***
  // constructor(
  //  public title: string,
  //  public description: string,
  //  public date: Date,
  //  public duration: number,
  //  public location: string,
  //  public category: string,
  // ) {}
  //
  constructor(
    public title: string,
    public category: string,
    public shortDescription: string,
    public contents: string,
    public duration: number
  ) {}
  }
