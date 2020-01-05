import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/dtos/news';
import { CustomerNews } from 'src/app/dtos/customer-news';
import { NewsService } from 'src/app/services/news.service';
import { CustomerNewsService } from 'src/app/services/customer-news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  latestNews: News[];
  oldNews: News[];
  boolOldNews: boolean = false;
  customerNews: CustomerNews[];
  oldCustomerNews: News[];
  latestCustomerNews: News[];

  constructor(private newsService: NewsService, private customerNewsService: CustomerNewsService) { }

  ngOnInit() {
    this.getAllCustomerNews();
    this.getOldAndLatestCustomerNews();
  }

  private getAllCustomerNews() {
    this.customerNewsService.getCustomerNews().subscribe(
      (customerNews: CustomerNews[]) => {
        this.customerNews = customerNews;
      }
    );
  }

  private getOldAndLatestCustomerNews() {
    let oldNews: News[];
    let latestNews: News[];
    this.customerNews.forEach( function(customerNewsEntry) {
        if (customerNewsEntry.read === true) {
          oldNews.push(customerNewsEntry.news);
        } else {
          latestNews.push(customerNewsEntry.news);
        }
      }
    );
    this.oldCustomerNews = oldNews;
    this.latestCustomerNews = latestNews;
  }

  private getAllNews() {
    this.newsService.getAllNews().subscribe(
      (news: News[]) => {
        this.latestNews = news;
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
