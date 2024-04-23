import { FilterService } from './filter.service';
import { HttpClient } from '@angular/common/http';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed, waitForAsync } from '@angular/core/testing';
import {
  MOCK_FILTER_1,
  MOCK_FILTER_2,
} from '../testing/services/filter.service-stub';

describe(FilterService.name, () => {
  let service: FilterService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = new FilterService(httpClient);
  }));

  afterEach(() => {
    httpTestingController.verify();
  });

  describe('findAll', () => {
    it('should make a GET request', () => {
      service.findAll().subscribe((result) => {
        expect(result).toEqual([MOCK_FILTER_1, MOCK_FILTER_2]);
      });

      httpTestingController
        .expectOne({
          url: FilterService.BASE_API_URL,
          method: 'GET',
        })
        .flush([MOCK_FILTER_1, MOCK_FILTER_2]);
    });
  });

  describe('create', () => {
    it('should make a GET request', () => {
      service.create(MOCK_FILTER_1).subscribe((result) => {
        expect(result).toEqual(MOCK_FILTER_1);
      });

      httpTestingController
        .expectOne({
          url: FilterService.BASE_API_URL,
          method: 'POST',
        })
        .flush(MOCK_FILTER_1);
    });
  });
});
