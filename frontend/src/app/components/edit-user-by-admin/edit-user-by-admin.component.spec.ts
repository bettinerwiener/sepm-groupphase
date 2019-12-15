import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditUserByAdminComponent } from './edit-user-by-admin.component';

describe('EditUserByAdminComponent', () => {
  let component: EditUserByAdminComponent;
  let fixture: ComponentFixture<EditUserByAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditUserByAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditUserByAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
