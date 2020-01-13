import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './components/header/header.component';
import {FooterComponent} from './components/footer/footer.component';
import {HomeComponent} from './components/home/home.component';
import {LoginComponent} from './components/login/login.component';
import {MessageComponent} from './components/message/message.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {httpInterceptorProviders} from './interceptors';
import { ConcertComponent } from './components/concert/concert.component';
import { TheatreComponent } from './components/theatre/theatre.component';
import { FilmComponent } from './components/film/film.component';
import { RegisterComponent } from './components/register/register.component';
import { SearchAreaComponent } from './components/search-area/search-area.component';
import { LocationsComponent } from './components/locations/locations.component';
import { ProfileComponent } from './components/profile/profile.component';
import { OrdersComponent } from './components/orders/orders.component';
import { CartComponent } from './components/cart/cart.component';
import { SliderComponent } from './components/slider/slider.component';
import { EventListItemComponent } from './components/event-list-item/event-list-item.component';
import { OrderComponent } from './components/order/order.component';
import { SearchUserComponent } from './components/search-user/search-user.component';
import { EditUserByAdminComponent } from './components/edit-user-by-admin/edit-user-by-admin.component';

import { CreateEventComponent } from './components/create-event/create-event.component';
import { EventItemComponent } from './components/event-item/event-item.component';
import { CreateLocationComponent} from './components/create-location/create-location.component';
import { SeatplanComponent } from './components/seatplan/seatplan.component';
import { CreateEventPerformanceComponent } from './components/create-event-performance/create-event-performance.component';
import { CreateRoomComponent } from './components/create-room/create-room.component';
import { CreateSeatplanComponent } from './components/create-seatplan/create-seatplan.component';
import { CreateEventNewsComponent } from './components/create-event-news/create-event-news.component';
import { NewsItemComponent } from './components/news-item/news-item.component';
import { EditNewsComponent } from './components/edit-news/edit-news.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    MessageComponent,
    FilmComponent,
    ConcertComponent,
    TheatreComponent,
    RegisterComponent,
    SearchAreaComponent,
    LocationsComponent,
    ProfileComponent,
    OrdersComponent,
    CartComponent,
    EventListItemComponent,
    SliderComponent,
    OrderComponent,

    SearchUserComponent,
    EditUserByAdminComponent,
    CreateEventComponent,
    EventItemComponent,
    SeatplanComponent,
    CreateEventPerformanceComponent,
    CreateLocationComponent,
    CreateRoomComponent,
    CreateSeatplanComponent,
    CreateEventNewsComponent,
    NewsItemComponent,
    EditNewsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    FontAwesomeModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule {
}
