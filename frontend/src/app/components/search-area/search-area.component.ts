import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'search-area',
  templateUrl: './search-area.component.html',
  styleUrls: ['./search-area.component.scss']
})
export class SearchAreaComponent implements OnInit {

  @Input() category: string;
  constructor() { }

  ngOnInit() {
  }

}
