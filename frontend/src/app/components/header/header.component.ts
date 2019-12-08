import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import { faShoppingCart, faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  faShoppingCart = faShoppingCart
  faUser = faUser
  username;

  constructor(public authService: AuthService) { }

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.username = this.authService.getUserName();
    } else {
      this.username = 'Max Mustermann';
    }
  }

}
