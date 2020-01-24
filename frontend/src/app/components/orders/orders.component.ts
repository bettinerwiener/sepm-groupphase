import { Component, OnInit } from '@angular/core';
import {OrdersService} from '../../services/orders.service';
import {AuthService} from '../../services/auth.service';
import {Order} from '../../dtos/order';
import { error } from 'protractor';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  error = false;
  errorMessage = '';
  orders: Order[];
   
  constructor(private orderService: OrdersService, private authService: AuthService) {}

  ngOnInit() {
    this.loadOrders();
    
  }

  private loadOrders() {
    this.orderService.getOrders().subscribe(
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
