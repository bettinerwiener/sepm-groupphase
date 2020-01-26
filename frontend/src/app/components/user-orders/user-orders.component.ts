import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Order } from 'src/app/dtos/order';
import { OrdersService } from 'src/app/services/orders.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'user-orders',
  templateUrl: './user-orders.component.html',
  styleUrls: ['./user-orders.component.scss']
})
export class UserOrdersComponent implements OnInit {


  error = false;
  errorMessage = '';
  orders: Order[];
  email: string;
  constructor(private orderService: OrdersService, private authService: AuthService, private _Activatedroute:ActivatedRoute) { 
    this.email = this._Activatedroute.snapshot.paramMap.get("email");
  }

  ngOnInit() {
    this.loadOrders();
    
  }

  private loadOrders() {
    this.orderService.getOrdersByEmail(this.email).subscribe(
      (orders: Order[]) => {
        this.orders = orders;
        console.log(this.orders[0]);
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  displayError(errorMessage) {
    this.errorMessage = errorMessage;
    this.error = true;
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
