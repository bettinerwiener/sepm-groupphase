import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSeatplanComponent } from './create-seatplan.component';

describe('CreateSeatplanComponent', () => {
  let component: CreateSeatplanComponent;
  let fixture: ComponentFixture<CreateSeatplanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSeatplanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSeatplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
