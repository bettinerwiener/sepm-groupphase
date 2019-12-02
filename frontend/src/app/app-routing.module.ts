import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';


import {ConcertComponent} from './components/concert/concert.component';
import {MoviesComponent} from './components/movies/movies.component';
import {TheatreComponent} from './components/theatre/theatre.component';
import {CabaretComponent} from './components/cabaret/cabaret.component';
import {LocationsComponent} from './components/locations/locations.component';

import {ProfileComponent} from './components/profile/profile.component';
import {OrdersComponent} from './components/orders/orders.component';
import {CartComponent} from './components/cart/cart.component';


import {AuthGuard} from './guards/auth.guard';
import {MessageComponent} from './components/message/message.component';

import {CreateEventComponent} from './components/create-event/create-event.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'concerts', component: ConcertComponent},
  {path: 'movies', component: MoviesComponent},
  {path: 'theatre', component: TheatreComponent},
  {path: 'cabaret', component: CabaretComponent},
  {path: 'locations', component: LocationsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'orders', component: OrdersComponent},
  {path: 'message', canActivate: [AuthGuard], component: MessageComponent},
  {path: 'create-event', component: CreateEventComponent}
  // {path: 'create-event', canActivate: [AuthGuard], component: CreateEventComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
