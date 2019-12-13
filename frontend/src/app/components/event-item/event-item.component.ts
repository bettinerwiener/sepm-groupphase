import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { EventObject } from 'src/app/dtos/event-object';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import { Ticket } from 'src/app/dtos/ticket';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.scss']
})
export class EventItemComponent implements OnInit {

  imageSource: string = 'https://dl1.cbsistatic.com/i/r/2018/08/09/b6ca69f8-f123-408c-9b1f-ea3f9cf1fb17/resize/620xauto/8787947d1d00135d3f2ed512e56bee72/concert-crowd.jpg';
  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;
  selectSeatBool: boolean = false;
  eventObject: EventObject;
  performances: Array<EventPerformance>;
  id: number = 0;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: TicketService
  ) { }

  selectSeats(performance:EventPerformance): void {
    performance.seatSelection = !performance.seatSelection;
    console.log(performance);
    
  }

  ngOnInit() {
    var id: number = parseInt(this.route.snapshot.paramMap.get('id'));
    this.service.getEvent(id).subscribe(
      (eventObj: EventObject) => {
        this.eventObject = eventObj;

      } 
    )

    this.service.getPerformancesByEventId(id).subscribe(
      (performances: Array<EventPerformance>) => {
        this.performances = performances;
        console.log("performances");
        
        console.log(performances);
        

        for(let perf of performances){

        this.service.getTicketsByPerformanceId(perf.id).subscribe(
          (tickets: Array<Ticket>) => {
            perf.tickets = tickets;
            console.log(tickets);
            console.log("tickets");
            
          }
        )

        }
      }
    )
  }
}
