import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filter } from '../models/filters.models';

@Injectable()
export class FilterService {
  static BASE_API_URL = '/rest/filters';

  constructor(private http: HttpClient) {}

  findAll(): Observable<Filter[]> {
    return this.http.get<Filter[]>(FilterService.BASE_API_URL);
  }

  create(filter: Filter): Observable<Filter> {
    return this.http.post<Filter>(FilterService.BASE_API_URL, filter);
  }
}
