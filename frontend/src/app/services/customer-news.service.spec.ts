import { TestBed } from '@angular/core/testing';

import { CustomerNewsService } from './customer-news.service';

describe('CustomerNewsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomerNewsService = TestBed.get(CustomerNewsService);
    expect(service).toBeTruthy();
  });
});
