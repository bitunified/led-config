import { TestBed, inject } from '@angular/core/testing';

import { ProductcodeService } from './productcode.service';

describe('ProductcodeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ProductcodeService]
    });
  });

  it('should ...', inject([ProductcodeService], (service: ProductcodeService) => {
    expect(service).toBeTruthy();
  }));
});
