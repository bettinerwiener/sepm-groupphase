import { Component, OnInit, Input} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import {PdfService} from '../../services/pdf.service';
import {Ticket} from '../../dtos/ticket';
@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  faCalendarDay = faCalendarDay
  faMapMarkerAlt = faMapMarkerAlt

  title: string;
  @Input() description: string;
  @Input() date: Date;
  @Input() location: string;
  @Input() number: number;
  @Input() status: string;
  @Input() price: number;
  @Input() tickets: Ticket[];

  constructor(private pdfService: PdfService) { }

  error = false;
  errorMessage = '';
  showTickets = false;


  ngOnInit() {
    console.log(this.tickets)
    this.title = this.tickets[0].performance.event.title;
    this.description = this.tickets[0].performance.event.shortDescription;
    this.date = this.tickets[0].performance.date;
    this.location= this.tickets[0].performance.room.location.name;
    this.price = this.calculatePrice();
    this.number = this.tickets.length;
    this.status = this.tickets[0].status;
  }

  calculatePrice() : number {
    let price = 0;
    this.tickets.forEach(ticket => {
      price += ticket.price;     
    });
    return price;
  }

  getTicket(id: number) {
    this.pdfService.getTicket(id).subscribe(x => {

      var newBlob = new Blob([x], { type: "application/pdf" });

      // IE
      if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          window.navigator.msSaveOrOpenBlob(newBlob);
          return;
      }

      const data = window.URL.createObjectURL(newBlob);
      var link = document.createElement('a');
      link.href = data;
      link.download = "ticket" + id + ".pdf";
     
      //delay for firefox
      link.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
      setTimeout(function () {
          window.URL.revokeObjectURL(data);
          link.remove();
      }, 100);
  });
  }

  toggleShowTickets() {
    this.showTickets = !this.showTickets;
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (typeof error.error === 'object') {
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error;
    }
  }

}
