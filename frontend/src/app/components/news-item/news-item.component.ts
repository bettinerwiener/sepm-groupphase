import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { Ticket } from 'src/app/dtos/ticket';
import { Order } from 'src/app/dtos/order';
import { TicketDto } from 'src/app/dtos/ticket-dto';
import { NewsService } from 'src/app/services/news-service.service';
import { News } from 'src/app/dtos/news';

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
  id: number = 0;
  selectedTickets: Array<Ticket> = new Array<Ticket>();
  submittedTickets: Array<TicketDto> = new Array<TicketDto>();
  load: boolean = false;
  order: Order;
  base64textString: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: NewsService
  ) { }
  
  ngOnInit() {
    var id: number = parseInt(this.route.snapshot.paramMap.get('id'));
    this.service.getNewsById(id).subscribe(
      (news: News) => {
        this.news = news;
        console.log(news);
        
      }
    )
  }

  handleFileSelect(evt){
    var files = evt.target.files;
    var file = files[0];

  if (files && file) {
      var reader = new FileReader();

      reader.onload =this._handleReaderLoaded.bind(this);

      reader.readAsBinaryString(file);
  }
}



_handleReaderLoaded(readerEvt) {
   var binaryString = readerEvt.target.result;
          this.base64textString= btoa(binaryString);
          console.log(btoa(binaryString));
  }

  onFileSelected(event):void {
    console.log(event.target.files[0]);
    
  }

  
}

