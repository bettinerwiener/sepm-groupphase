import { Component, OnInit } from '@angular/core';
import { News } from 'src/app/dtos/news';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  latestNews: News[];
  oldNews: News[];
  boolOldNews: boolean = false;

  constructor(private newsService: NewsService) { }

  ngOnInit() {
    this.getAllNews();
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
