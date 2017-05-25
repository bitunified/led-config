import { TestBed, inject } from '@angular/core/testing';

import { ModelserviceService } from './modelservice.service';

describe('ModelserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ModelserviceService]
    });
  });

  it('should be created', inject([ModelserviceService], (service: ModelserviceService) => {
    expect(service).toBeTruthy();
  }));
});
