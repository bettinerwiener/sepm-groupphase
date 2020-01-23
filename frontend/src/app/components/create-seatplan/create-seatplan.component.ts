import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Seat } from '../../dtos/seat';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-seatplan',
  templateUrl: './create-seatplan.component.html',
  styleUrls: ['./create-seatplan.component.scss']
})
export class CreateSeatplanComponent implements OnInit {
  seatForm: FormGroup;
  selectedSection: string;
  selecting: boolean = false;
  notAssigned: boolean = false;
  priceFactor: number = 1.0;
  priceFactorValid: boolean = true;

  constructor(
    private formbuilder: FormBuilder,
  ) {
    this.seatForm = this.formbuilder.group({
      section: ['', Validators.required]
    });
  }

  @Input() seatplan: Array<Array<Seat>>;
  @Output() seatEmitter: EventEmitter<Array<Array<Seat>>> = new EventEmitter();

  stagewidth: string;

  ngOnInit() {
    var stagewidth = this.seatplan[0].length;
    if (stagewidth > 1) {
      this.stagewidth = stagewidth * 39 + "px";
    } else {
      this.stagewidth = 50 + "px";
    }

  }

  select(seat: Seat): void {
    this.selecting = true;
    if (this.selectedSection == "") {
      return;
    }

    seat.section.letter = this.selectedSection;
    seat.section.priceFactor = this.priceFactor;
    if (this.priceFactor < 0 || this.priceFactor > 20) {
      this.priceFactorValid = false;
    }

    for (let rowUnit of this.seatplan) {
      for (let seatUnit of rowUnit) {
        if (seatUnit.section.letter == null) {
          this.notAssigned = true;
          return;
        }
      }
    }
    this.notAssigned = false;

    this.seatEmitter.emit(this.seatplan);
  }
}
