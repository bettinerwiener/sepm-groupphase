import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import {first, last} from 'rxjs/operators';
import { Ticket } from 'src/app/dtos/ticket';

@Component({
  selector: 'event-list-item',
  templateUrl: './event-list-item.component.html',
  styleUrls: ['./event-list-item.component.scss'],
  providers: [ɵROUTER_PROVIDERS]
})
export class EventListItemComponent implements OnInit {

  faCalendarDay = faCalendarDay;
  faMapMarkerAlt = faMapMarkerAlt;

  @Input() id: number;
  @Input() title: string;
  @Input() description: string;
  @Input() location: string;
  @Input() price: number;
  private sortedPerformanceDates: EventPerformance[];
  firstPerformanceDate: string;
  lastPerformanceDate: string;
  hasPerformance: boolean = true;
  lowestPrice: number;
  error: boolean = false;
  errorMessage: string = 'There went something wrong while displaying these events';

  constructor(private router: Router, private eventService: EventService, private ticketService: TicketService) { }

  ngOnInit() {
    this.getFirstAndLastPerformance(this.id);
    // this.getLowestPrice(this.id);
  }

  getId(id: number) {
    this.router.navigate(['/event/', id]).then(
      () => window.location.reload());
  }

  getFirstAndLastPerformance(id: number) {
    this.ticketService.getPerformancesByEventId(id).subscribe(
      (retPerformances: EventPerformance[]) => {
        if (retPerformances.length < 1) {
          this.hasPerformance = false;
        } else {
          this.hasPerformance = true;
          let dateString: string[] = retPerformances[0].date.toString().split('-');
          const firstPerformance = new Date(Number(dateString[0]), Number(dateString[1]) - 1,
            Number(dateString[2].slice(0, 2)), 0, 0, 0);
          this.firstPerformanceDate = (firstPerformance.getDate()) + '-' +
            (firstPerformance.getMonth() + 1) + '-' + firstPerformance.getFullYear();

          dateString = retPerformances[retPerformances.length - 1].date.toString().split('-');
          const lastPerformance = new Date(Number(dateString[0]), Number(dateString[1]) - 1,
            Number(dateString[2].slice(0, 2)), 0, 0, 0);
          this.lastPerformanceDate = (lastPerformance.getDate()) + '-' +
            (lastPerformance.getMonth() + 1) + '-' + lastPerformance.getFullYear();
        }
      },
      (error) => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  // getLowestPrice(id: number) {
  //   let ticketPrices: number[];
  //   this.ticketService.getPerformancesByEventId(id).subscribe(
  //     (retPerformances: EventPerformance[]) => {
  //       retPerformances.forEach( function (retPerformance) {
  //         this.ticketService.getTicketsByPerformanceId(retPerformance.id).subscribe(
  //           (retTickets: Ticket[]) => {
  //             console.log(retTickets);
  //             retTickets.forEach( function (retTicketPrice) {
  //               ticketPrices.push(retTicketPrice[0]);
  //             }
  //             );
  //           }
  //         );
  //       }
  //       );
  //     }
  //   );
  //   console.log(ticketPrices);
  //   ticketPrices.sort(
  //     (a: number, b: number) => {
  //       return a - b;
  //     }
  //   );
  //   this.lowestPrice = ticketPrices[0];
  // }


  // private sortTickets(a, b) {
  //   if ( a[6] === b[6] ) {
  //     return 0;
  //   } else {
  //     return (a[6] < b[6]) ? -1 : 1;
  //   }
  // }

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
