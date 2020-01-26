import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { Router, ɵROUTER_PROVIDERS } from '@angular/router';
import { EventService } from 'src/app/services/event.service';
import { GlobalEvent } from 'src/app/dtos/global-event';
import { TicketService } from 'src/app/services/ticket.service';
import { EventPerformance } from 'src/app/dtos/event-performance';
import {first, last} from 'rxjs/operators';
import { Ticket } from 'src/app/dtos/ticket';
import { SafeUrl, DomSanitizer } from '@angular/platform-browser';

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
  @Input() image: Blob;
  imageURL: SafeUrl;
  private sortedPerformanceDates: EventPerformance[];
  firstPerformanceDate: string;
  lastPerformanceDate: string;
  hasPerformance: boolean = true;
  lowestPrice: number;
  ticketsBool: boolean = true;
  error: boolean = false;
  errorMessage: string = 'There went something wrong while displaying these events';

  constructor(
    private router: Router,
    private eventService: EventService,
    private ticketService: TicketService,
    private sanitizer: DomSanitizer
    ) { }

  ngOnInit() {
    this.getFirstAndLastPerformance(this.id);
    this.getImage();
    this.getMinPrice();
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

  getMinPrice() {
    this.eventService.getMinPricePerEvent(this.id).subscribe(
      (price: number) => {
        if (price != null ) {
          this.lowestPrice = price;
        } else {
          this.ticketsBool = false;
        }
      },
      (error) => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  getImage() {
    this.eventService.getImage(this.id).subscribe(
      (image: any) => {
        this.image = image.body;
        const reader = new FileReader();
        reader.readAsDataURL(this.image);
        let result;
        reader.onloadend = (event: Event) => {
          result = reader.result;
          this.imageURL = this.sanitizer.bypassSecurityTrustUrl(result);
        };
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
