import { TestBed, inject } from '@angular/core/testing';

import { ProductcodeService } from './productcode.service';

describe('ProductcodeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductcodeService]
    });
  });

  it('should be created', inject([ProductcodeService], (service: ProductcodeService) => {
    expect(service).toBeTruthy();
  }));
});
