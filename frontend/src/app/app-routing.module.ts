import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {ConcertComponent} from './components/concert/concert.component';
import {FilmComponent} from './components/film/film.component';
import {TheatreComponent} from './components/theatre/theatre.component';
import {LocationsComponent} from './components/locations/locations.component';
import {ProfileComponent} from './components/profile/profile.component';
import {OrdersComponent} from './components/orders/orders.component';
import {CartComponent} from './components/cart/cart.component';
import {AuthGuard} from './guards/auth.guard';
import {MessageComponent} from './components/message/message.component';
import {SearchUserComponent} from './components/search-user/search-user.component';
import {EditUserByAdminComponent} from './components/edit-user-by-admin/edit-user-by-admin.component';
import { SeatplanComponent } from './components/seatplan/seatplan.component';
import { EventItemComponent } from './components/event-item/event-item.component';
import { EventListItemComponent } from './components/event-list-item/event-list-item.component';
import {CreateEventComponent} from './components/create-event/create-event.component';
import { CreateEventPerformanceComponent } from './components/create-event-performance/create-event-performance.component';
import { CreateLocationComponent } from './components/create-location/create-location.component';
import { CreateRoomComponent } from './components/create-room/create-room.component';
import { CreateEventNewsComponent } from './components/create-event-news/create-event-news.component'
import { NewsItemComponent } from './components/news-item/news-item.component'


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'concerts', component: ConcertComponent},
  {path: 'films', component: FilmComponent},
  {path: 'theatre', component: TheatreComponent},
  {path: 'locations', component: LocationsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'profile', canActivate: [AuthGuard], component: ProfileComponent},
  {path: 'orders', canActivate: [AuthGuard], component: OrdersComponent},
  {path: 'message', canActivate: [AuthGuard], component: MessageComponent},
  {path: 'admin', component: SearchUserComponent},
  {path: 'admin/edit/:username', component: EditUserByAdminComponent},
  {path: 'test', component: SeatplanComponent},
  {path: 'event/:id', component: EventItemComponent},
  {path: 'event-list-item', component: EventListItemComponent},
  {path: 'create-event', component: CreateEventComponent},
  {path: 'create-event-performance', component: CreateEventPerformanceComponent},
  {path: 'create-location', component: CreateLocationComponent},
  {path: 'create-room', component: CreateRoomComponent},
  {path: 'create-event-news', component: CreateEventNewsComponent},
  {path: 'news/:id', component: NewsItemComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
