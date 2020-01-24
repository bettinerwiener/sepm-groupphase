import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import {PdfService} from '../../services/pdf.service';
import {OrdersService} from '../../services/orders.service';
import {Ticket} from '../../dtos/ticket';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  faCalendarDay = faCalendarDay
  faMapMarkerAlt = faMapMarkerAlt

  form: FormGroup;
  formData: [];
  title: string;
  @Input() description: string;
  @Input() id: number;
  @Input() admin: number;
  @Input() date: Date;
  @Input() location: string;
  @Input() number: number;
  @Input() status: string;
  @Input() price: number;
  @Input() tickets: Ticket[];
  t: Ticket[];
  @Output() errorEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private toastr: ToastrService, private pdfService: PdfService, private formBuilder: FormBuilder, private ordersService: OrdersService) {
    this.form = this.formBuilder.group({
      formData: []
    })
   }

  error = false;
  errorMessage = '';
  showTickets = false;


  ngOnInit() {
    this.title = this.tickets[0].performance.event.title;
    this.description = this.tickets[0].performance.event.shortDescription;
    this.date = this.tickets[0].performance.date;
    this.location= this.tickets[0].performance.room.location.name;
    this.price = this.calculatePrice();
    this.number = this.tickets.length;
    this.status = this.tickets[0].status;
    this.t = this.tickets;
  }

  calculatePrice() : number {
    let price = 0;
    this.tickets.forEach(ticket => {
      price += ticket.price;    
      ticket.selected = true;
    });
    return price;
  }

  getTicket(id: number) {
    console.log("getTicket " + id)
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

  getInvoice() {
    this.pdfService.getInvoice(this.id).subscribe(x => {
      var newBlob = new Blob([x], { type: "application/pdf" });
      // IE
      if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          window.navigator.msSaveOrOpenBlob(newBlob);
          return;
      }

      const data = window.URL.createObjectURL(newBlob);
      var link = document.createElement('a');
      link.href = data;
      link.download = "invoice" + this.id + ".pdf";
     
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
    console.log(this.showTickets);
  }
  purchaseTickets() {
    let toBuy = [];
    for(let i of this.tickets) {
      if(i.selected) {
        toBuy.push({id: i.id});
      }
    }
    if(this.admin==1) {
      this.ordersService.purchaseTicketsAsAdmin(toBuy).subscribe(x => {
        this.toastr.success('Success!', 'Tickets gekauft');
        window.location.reload()
      }, error => {
        this.defaultServiceErrorHandling(error);
      });
    } else {
      this.ordersService.purchaseTickets(toBuy).subscribe(x => {
        this.toastr.success('Success!', 'Tickets gekauft');
        window.location.reload()
      }, error => {
        this.defaultServiceErrorHandling(error);
      });
    }

  
  }

  cancelReservation() {
    let toCancel = [];
    for(let i of this.tickets) {
      if(i.selected) {
        toCancel.push({id: i.id});
      }
    }
  
    this.ordersService.cancelReservation(toCancel).subscribe(x => {
      this.toastr.success('Success!', 'Ticket storniert');
      window.location.reload()
    },error => {
      this.defaultServiceErrorHandling(error);
    });
  }

  cancelPurchase() {
    let toCancel = [];
    for(let i of this.tickets) {
      if(i.selected) {
        toCancel.push({id: i.id});
      }
    }
  
    this.ordersService.cancelPurchase(toCancel).subscribe(x => {

      var newBlob = new Blob([x], { type: "application/pdf" });

      // IE
      if (window.navigator && window.navigator.msSaveOrOpenBlob) {
          window.navigator.msSaveOrOpenBlob(newBlob);
          return;
      }

      const data = window.URL.createObjectURL(newBlob);
      var link = document.createElement('a');
      link.href = data;
      link.download = "stornorechnung" + ".pdf";
     
      //delay for firefox
      link.dispatchEvent(new MouseEvent('click', { bubbles: true, cancelable: true, view: window }));
      setTimeout(function () {
          window.URL.revokeObjectURL(data);
          link.remove();
      }, 100);

      this.toastr.success('Success!', 'Ticket storniert');
      window.location.reload()
      

  }, 
  error => {
    console.log(error);
      this.defaultServiceErrorHandling(error);
  });
  }

  vanishError() {
    this.error = false;
  }


  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (typeof error.error === 'object') {
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error;
    }
    this.toastr.error(error.message, error.status, {
      timeOut: 3000
    });
  }

}
