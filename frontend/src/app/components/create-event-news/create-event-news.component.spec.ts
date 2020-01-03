import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateEventNewsComponent } from './create-event-news.component';

describe('CreateEventNewsComponent', () => {
  let component: CreateEventNewsComponent;
  let fixture: ComponentFixture<CreateEventNewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateEventNewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateEventNewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
