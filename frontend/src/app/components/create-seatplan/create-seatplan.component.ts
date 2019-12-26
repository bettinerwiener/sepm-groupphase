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
  }

  submit():void {
    for (let rowUnit of this.seatplan) {
      for (let seatUnit of rowUnit) {
        if (seatUnit.section.letter == null) {
          console.error("All seats must have to be assigned to a sector.");
          return;
        }
      }
    }

    this.seatEmitter.emit(this.seatplan);

  }


}
