import { TestBed } from '@angular/core/testing';

import { EventPerformanceService } from './event-performance.service';

describe('EventPerformanceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EventPerformanceService = TestBed.get(EventPerformanceService);
    expect(service).toBeTruthy();
  });
});
