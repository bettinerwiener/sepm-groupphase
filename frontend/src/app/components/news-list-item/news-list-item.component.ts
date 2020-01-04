import { Component, OnInit, Input } from '@angular/core';
import { faCalendarDay } from '@fortawesome/free-solid-svg-icons';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';


@Component({
  selector: 'news-list-item',
  templateUrl: './news-list-item.component.html',
  styleUrls: ['./news-list-item.component.scss'],
  providers: [ɵROUTER_PROVIDERS]
})
export class NewsListItemComponent implements OnInit {

  faCalendarDay = faCalendarDay;

  @Input() id: number;
  @Input() title: string;
  @Input() shortDescription: string;
  @Input() publishedAt: Date;
  @Input() image: string;

  ngOnInit() {
    console.log(this.image);
  }

  constructor(private router: Router) { }

  getId(id: number) {
    this.router.navigate(['/news/', id]).then(
      () => window.location.reload());
  }



}
