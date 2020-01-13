import { Component, OnInit, Input } from '@angular/core';
import { faCalendarDay } from '@fortawesome/free-solid-svg-icons';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';
import { User } from 'src/app/dtos/user';
import { AuthService } from 'src/app/services/auth.service';
import { CustomerNews } from 'src/app/dtos/customer-news';
import { News } from 'src/app/dtos/news';
import { CustomerNewsService } from 'src/app/services/customer-news.service';


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
  image64: any;
  error: boolean = false;
  errorMessage: string = 'There was a problem while loading this news entry';

  ngOnInit() {
    this.image64 = window.atob(this.image);
  }

  constructor(
    private router: Router,
    private authService: AuthService,
    private customerNewsService: CustomerNewsService) { }

  readNews(id: number) {
    this.router.navigate(['/news/', id]).then(
      () => window.location.reload());
    this.setCustomerNewsToRead(id);
  }

  setCustomerNewsToRead(newsId: number) {
    const user = new User(
      null,
      'dummy',
      'dummy',
      this.authService.getUserName(),
      'dummy',
      false,
      true
    );

    const news = new News(
      newsId,
      'dummy',
      'dummy',
      'dummy',
      null,
      null
    );

    const customerNews = new CustomerNews(
      user,
      news,
      true
    );

    this.customerNewsService.setCustomerNewsToRead(customerNews).subscribe(
      () => {},
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }



}
