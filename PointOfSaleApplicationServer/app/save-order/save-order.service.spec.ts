import { TestBed, inject } from '@angular/core/testing';

import { SaveOrderService } from './save-order.service';

describe('SaveOrderService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SaveOrderService]
    });
  });

  it('should be created', inject([SaveOrderService], (service: SaveOrderService) => {
    expect(service).toBeTruthy();
  }));
});
