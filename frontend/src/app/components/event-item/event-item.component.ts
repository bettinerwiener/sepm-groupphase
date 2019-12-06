import { Component, OnInit } from '@angular/core';
import { faCalendarDay, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { SeatplanComponent } from '../seatplan/seatplan.component';

@Component({
  selector: 'app-event-item',
  templateUrl: './event-item.component.html',
  styleUrls: ['./event-item.component.scss']
})
export class EventItemComponent implements OnInit {

  imageSource: string = "https://dl1.cbsistatic.com/i/r/2018/08/09/b6ca69f8-f123-408c-9b1f-ea3f9cf1fb17/resize/620xauto/8787947d1d00135d3f2ed512e56bee72/concert-crowd.jpg";
  faCalendarDay = faCalendarDay
  faMapMarkerAlt = faMapMarkerAlt
  selectSeatBool: boolean = false;
  //imageHeight: number = +document.getElementById('image').style.height;

  constructor() { }

  selectSeats(): void {
    this.selectSeatBool = !this.selectSeatBool;
  }

  ngOnInit() {

  }


}
