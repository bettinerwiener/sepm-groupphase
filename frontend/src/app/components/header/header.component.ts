import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import { faShoppingCart, faUser } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  faShoppingCart = faShoppingCart;
  faUser = faUser;
  constructor(public authService: AuthService) { }

  ngOnInit() {
  }

}
