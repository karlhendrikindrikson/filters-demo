import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FilterListComponent } from './filter-list.component';
import { FilterService } from '../../services/filter.service';
import {
  FilterServiceStub,
  MOCK_FILTER_1,
  MOCK_FILTER_2,
} from '../../testing/services/filter.service-stub';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogStub } from '../../testing/mat-dialog-stub';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { of, Subject } from 'rxjs';
import createSpyObj = jasmine.createSpyObj;
import SpyObj = jasmine.SpyObj;
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { FilterFormComponent } from './filter-form/filter-form.component';

describe(FilterListComponent.name, () => {
  let component: FilterListComponent;
  let fixture: ComponentFixture<FilterListComponent>;
  let filterService: FilterService;
  let matDialog: MatDialog;
  let _route: ActivatedRoute;
  let queryParamsSpy: SpyObj<ParamMap>;

  beforeEach(async () => {
    queryParamsSpy = createSpyObj(['has', 'get']);
    await TestBed.configureTestingModule({
      declarations: [FilterListComponent],
      imports: [MatCardModule, MatTableModule, MatButtonModule, MatIconModule],
      providers: [
        { provide: FilterService, useClass: FilterServiceStub },
        { provide: MatDialog, useClass: MatDialogStub },
        {
          provide: ActivatedRoute,
          useValue: {
            queryParamMap: of(queryParamsSpy),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(FilterListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    filterService = fixture.debugElement.injector.get(FilterService);
    matDialog = fixture.debugElement.injector.get(MatDialog);
    _route = fixture.debugElement.injector.get(ActivatedRoute);
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('ngOnInit', () => {
    it('should refresh filters', () => {
      const filterServiceMock = spyOn(filterService, 'findAll').and.returnValue(
        of([MOCK_FILTER_1, MOCK_FILTER_2]),
      );

      component.ngOnInit();

      expect(filterServiceMock).toHaveBeenCalled();
      expect(component.isLoading).toBeFalse();
      expect(component.filters).toEqual([MOCK_FILTER_1, MOCK_FILTER_2]);
    });

    describe('when useNonModalMode query parameter is not set', () => {
      it('isNonModalModeEnabled should be false', () => {
        queryParamsSpy.has.and.returnValue(false);

        component.ngOnInit();

        expect(component.isNonModalModeEnabled).toBeFalse();
        expect(queryParamsSpy.has).toHaveBeenCalledWith(
          FilterListComponent.NON_MODAL_MODE_QUERY_PARAM,
        );
        expect(queryParamsSpy.get).not.toHaveBeenCalled();
      });
    });

    describe('when useNonModalMode query parameter is false', () => {
      it('isNonModalModeEnabled should be false', () => {
        queryParamsSpy.has.and.returnValue(true);
        queryParamsSpy.get.and.returnValue('false');

        component.ngOnInit();

        expect(component.isNonModalModeEnabled).toBeFalse();
        expect(queryParamsSpy.has).toHaveBeenCalledWith(
          FilterListComponent.NON_MODAL_MODE_QUERY_PARAM,
        );
        expect(queryParamsSpy.get).toHaveBeenCalledWith(
          FilterListComponent.NON_MODAL_MODE_QUERY_PARAM,
        );
      });
    });

    describe('when useNonModalMode query parameter is true', () => {
      it('isNonModalModeEnabled should be true', () => {
        queryParamsSpy.has.and.returnValue(true);
        queryParamsSpy.get.and.returnValue('true');

        component.ngOnInit();

        expect(component.isNonModalModeEnabled).toBeTrue();
        expect(queryParamsSpy.has).toHaveBeenCalledWith(
          FilterListComponent.NON_MODAL_MODE_QUERY_PARAM,
        );
        expect(queryParamsSpy.get).toHaveBeenCalledWith(
          FilterListComponent.NON_MODAL_MODE_QUERY_PARAM,
        );
      });
    });
  });

  describe('openDialogForm', () => {
    describe('when isNonModalModeEnabled is true', () => {
      it('should open a draggable dialog with no backdrop', () => {
        const afterClosedSubject = new Subject<any>();
        const matDialogRef = {
          close: () => {},
          afterClosed: () => afterClosedSubject.asObservable(),
        } as MatDialogRef<any>;
        component.isNonModalModeEnabled = true;

        spyOn(matDialog, 'open').and.returnValue(matDialogRef);
        spyOn(component, 'refreshFilters').and.stub();

        component.openDialogForm();
        afterClosedSubject.next(true);

        expect(matDialog.open).toHaveBeenCalledWith(FilterFormComponent, {
          data: { filter: null, draggable: true },
          panelClass: ['resizeable-dialog', 'filter-form-dialog'],
          hasBackdrop: false,
        });
        expect(component.refreshFilters).toHaveBeenCalled();
      });
    });

    describe('when isNonModalModeEnabled is false', () => {
      it('should open a non draggable dialog', () => {
        const afterClosedSubject = new Subject<any>();
        const matDialogRef = {
          close: () => {},
          afterClosed: () => afterClosedSubject.asObservable(),
        } as MatDialogRef<any>;
        component.isNonModalModeEnabled = false;

        spyOn(matDialog, 'open').and.returnValue(matDialogRef);
        spyOn(component, 'refreshFilters').and.stub();

        component.openDialogForm();
        afterClosedSubject.next(false);

        expect(matDialog.open).toHaveBeenCalledWith(FilterFormComponent, {
          data: { filter: null, draggable: false },
          panelClass: ['resizeable-dialog', 'filter-form-dialog'],
          hasBackdrop: true,
        });
        expect(component.refreshFilters).not.toHaveBeenCalled();
      });
    });
  });
});
