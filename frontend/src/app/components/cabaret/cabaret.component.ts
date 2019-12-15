import { Component, OnInit, Input } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';

@Component({
  selector: 'app-cabaret',
  templateUrl: './cabaret.component.html',
  styleUrls: ['./cabaret.component.scss']
})
export class CabaretComponent implements OnInit {

  @Input() cabarets: GlobalEvent[];

  constructor() { }

  ngOnInit() {
  }

}
