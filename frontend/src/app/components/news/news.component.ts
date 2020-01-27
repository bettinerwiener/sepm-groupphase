import { Component, OnInit, Output } from '@angular/core';
import { CustomerNews } from 'src/app/dtos/customer-news';
import { NewsService } from 'src/app/services/news.service';
import { CustomerNewsService } from 'src/app/services/customer-news.service';
import { AuthService } from 'src/app/services/auth.service';
import { EventEmitter } from 'protractor';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  boolOldNews: boolean = false;
  latestCustomerNews: CustomerNews[];
  oldCustomerNews: CustomerNews[];
  username: string;

  constructor(private newsService: NewsService,
              private customerNewsService: CustomerNewsService,
              private authService: AuthService) { }

  ngOnInit() {
    this.getLatestCustomerNews();
    this.getOldCustomerNews();
  }

  private getOldCustomerNews() {
    this.customerNewsService.getCustomerNewsByCustomerAndRead(this.authService.getUserName(), true).subscribe(
      (customerNews: CustomerNews[]) => {
        this.oldCustomerNews = customerNews;
      }
    );
  }

  private getLatestCustomerNews() {
    this.customerNewsService.getCustomerNewsByCustomerAndRead(this.authService.getUserName(), false).subscribe(
      (customerNews: CustomerNews[]) => {
        this.latestCustomerNews = customerNews;
      }
    );
  }

  private getOldNews() {
    this.boolOldNews = true;
  }

  private getLatestNews() {
    this.boolOldNews = false;
  }

}
