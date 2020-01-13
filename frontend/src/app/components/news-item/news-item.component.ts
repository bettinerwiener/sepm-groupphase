import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Ticket } from 'src/app/dtos/ticket';
import { Order } from 'src/app/dtos/order';
import { TicketDto } from 'src/app/dtos/ticket-dto';
import { NewsService } from 'src/app/services/news.service';
import { News } from 'src/app/dtos/news';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { EventNewsService } from 'src/app/services/event-news.service';
import { EventNews } from 'src/app/dtos/event-news';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['./news-item.component.scss']
})
export class NewsItemComponent implements OnInit {

  imageSource: string = 'https://static-cdn.arte.tv/resize/97AI802JURCvpRMvNsDE_MNk_ZI=/400x400/smart/filters:strip_icc()/afem/uploads/a6d97e0a-50ff-463f-b025-229a88c04400.jpeg';
  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;
  selectSeatBool: boolean = false;
  news: News;
  eventNews: EventNews = null;
  id: number = 0;
  selectedTickets: Array<Ticket> = new Array<Ticket>();
  submittedTickets: Array<TicketDto> = new Array<TicketDto>();
  load: boolean = false;
  order: Order;
  imageURL: SafeUrl;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: NewsService,
    private eventNewsService: EventNewsService,
    private sanitizer: DomSanitizer
  ) { }
  
  ngOnInit() {
    var id: number = parseInt(this.route.snapshot.paramMap.get('id'));
    this.id = id;
    this.service.getNewsById(id).subscribe(
      (news: News) => {
        this.news = news;
        
        this.service.getImage(id).subscribe(
          (image: any) => {
            this.news.image = image.body;
            const reader = new FileReader();
            reader.readAsDataURL(this.news.image);
            let result;
            reader.onloadend = (event:Event) => {
              result = reader.result;
              this.imageURL = this.sanitizer.bypassSecurityTrustUrl(result);
            };
            
          },
          error => {
            //this.defaultServiceErrorHandling(error);
          }
        );
      }
    )
    this.eventNewsService.getEventForNews(id).subscribe(
      (eventNews:Array<EventNews>) => {
        this.eventNews = eventNews[0];
      }
    )


  }

  
}

