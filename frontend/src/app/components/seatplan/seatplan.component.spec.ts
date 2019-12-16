import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeatplanComponent } from './seatplan.component';

describe('SeatplanComponent', () => {
  let component: SeatplanComponent;
  let fixture: ComponentFixture<SeatplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeatplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeatplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
