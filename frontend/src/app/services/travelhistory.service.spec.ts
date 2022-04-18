import { TestBed } from '@angular/core/testing';

import { TravelhistoryService } from './travelhistory.service';

describe('TravelhistoryService', () => {
  let service: TravelhistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TravelhistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
