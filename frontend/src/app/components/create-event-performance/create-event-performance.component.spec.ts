import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEventPerformanceComponent } from './create-event-performance.component';

describe('CreateEventPerformanceComponent', () => {
  let component: CreateEventPerformanceComponent;
  let fixture: ComponentFixture<CreateEventPerformanceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateEventPerformanceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateEventPerformanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
