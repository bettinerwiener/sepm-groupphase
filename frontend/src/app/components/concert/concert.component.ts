import { Component, OnInit, Input } from '@angular/core';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-concert',
  templateUrl: './concert.component.html',
  styleUrls: ['./concert.component.scss']
})
export class ConcertComponent implements OnInit {

  @Input() concerts: GlobalEvent[];

  constructor(searchService: SearchService) { }

  ngOnInit() {
  }

}
