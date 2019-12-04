import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  swiperight(): void {
    let element = document.getElementById('slider');
    element.scroll({top: 0, left: 10000, behavior: 'smooth'});
  }

  swipeleft(): void {
    let element = document.getElementById('slider');
    element.scroll({top: 0, left: 0, behavior: 'smooth'});
  }
}
