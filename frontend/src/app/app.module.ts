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
import { CabaretComponent } from './components/cabaret/cabaret.component';
import { MoviesComponent } from './components/movies/movies.component';
import { RegisterComponent } from './components/register/register.component';
import { SearchAreaComponent } from './components/search-area/search-area.component';
import { LocationsComponent } from './components/locations/locations.component';
import { ProfileComponent } from './components/profile/profile.component';
import { OrdersComponent } from './components/orders/orders.component';
import { CartComponent } from './components/cart/cart.component';
import { SliderComponent } from './components/slider/slider.component';
import { EventListItemComponent } from './components/event-list-item/event-list-item.component';
import { OrderComponent } from './components/order/order.component';

import { CreateEventComponent } from './components/create-event/create-event.component';
import { SearchUserComponent } from './components/search-user/search-user.component';
import { EditUserByAdminComponent } from './components/edit-user-by-admin/edit-user-by-admin.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    MessageComponent,
    ConcertComponent,
    TheatreComponent,
    CabaretComponent,
    RegisterComponent,
    SearchAreaComponent,
    MoviesComponent,
    LocationsComponent,
    ProfileComponent,
    OrdersComponent,
    CartComponent,
    EventListItemComponent,
    SliderComponent,
    OrderComponent,

    CreateEventComponent,
    SearchUserComponent,
    EditUserByAdminComponent,
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
