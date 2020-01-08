import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { EventObject } from 'src/app/dtos/event-object';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Ticket } from 'src/app/dtos/ticket';
import { Order } from 'src/app/dtos/order';
import { TicketDto } from 'src/app/dtos/ticket-dto';

@Component({
  selector: 'app-news-item',
  templateUrl: './news-item.component.html',
  styleUrls: ['./news-item.component.scss']
})
export class NewsItemComponent implements OnInit {

  imageSource: string = 'https://dl1.cbsistatic.com/i/r/2018/08/09/b6ca69f8-f123-408c-9b1f-ea3f9cf1fb17/resize/620xauto/8787947d1d00135d3f2ed512e56bee72/concert-crowd.jpg';
  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;
  selectSeatBool: boolean = false;
  eventObject: EventObject;
  performances: Array<EventPerformance>;
  id: number = 0;
  selectedTickets: Array<Ticket> = new Array<Ticket>();
  submittedTickets: Array<TicketDto> = new Array<TicketDto>();
  load: boolean = false;
  order: Order;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: NewsService
  ) { }
  
  ngOnInit() {
    
  }

  
}

