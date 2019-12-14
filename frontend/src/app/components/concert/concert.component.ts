import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';

@Component({
  selector: 'app-concert',
  templateUrl: './concert.component.html',
  styleUrls: ['./concert.component.scss']
})
export class ConcertComponent implements OnInit {

  constructor(private searchService: SearchService) { }

  ngOnInit() {
  }

  searchConcerts() {
    // this.searchService.loadEvent().subscribe;
  }


}
