import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CabaretComponent } from './cabaret.component';

describe('CabaretComponent', () => {
  let component: CabaretComponent;
  let fixture: ComponentFixture<CabaretComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CabaretComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CabaretComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
