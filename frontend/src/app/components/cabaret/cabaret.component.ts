import { Component, OnInit, Input } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-cabaret',
  templateUrl: './cabaret.component.html',
  styleUrls: ['./cabaret.component.scss']
})
export class CabaretComponent implements OnInit {

  cabarets: GlobalEvent[];

  getCabarets(cabarets: GlobalEvent[]): void {
    this.cabarets = cabarets;
  }

  constructor() { }

  ngOnInit() {
  }

}
