import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import {
  CriteriaCondition,
  CriteriaType,
  Filter,
} from '../../models/filters.models';

export const MOCK_FILTER_1: Filter = {
  id: 1,
  name: 'Exercise Title',
  criteria: [
    {
      criteriaType: CriteriaType.TITLE,
      criteriaCondition: CriteriaCondition.STARTS_WITH,
      value: 'EX_',
    },
  ],
};

export const MOCK_FILTER_2: Filter = {
  id: 2,
  name: 'Year 2024',
  criteria: [
    {
      criteriaType: CriteriaType.DATE,
      criteriaCondition: CriteriaCondition.FROM,
      value: '01/01/2024',
    },
    {
      criteriaType: CriteriaType.DATE,
      criteriaCondition: CriteriaCondition.TO,
      value: '12/31/2024',
    },
  ],
};

@Injectable()
export class FilterServiceStub {
  findAll(): Observable<Filter[]> {
    return of([MOCK_FILTER_1, MOCK_FILTER_2]);
  }

  create(filter: Filter): Observable<Filter> {
    return of(filter);
  }
}
