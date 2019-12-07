import { TestBed } from '@angular/core/testing';

import { TopTenService } from './top-ten.service';

describe('TopTenService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TopTenService = TestBed.get(TopTenService);
    expect(service).toBeTruthy();
  });
});
