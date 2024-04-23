import { Component, OnDestroy, OnInit } from '@angular/core';
import { Filter } from '../../models/filters.models';
import { FilterService } from '../../services/filter.service';
import { finalize, first, Subject, takeUntil } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { FilterFormComponent } from './filter-form/filter-form.component';

@Component({
  selector: 'app-filter-list',
  templateUrl: './filter-list.component.html',
  styleUrls: ['./filter-list.component.scss'],
})
export class FilterListComponent implements OnInit, OnDestroy {
  static readonly NON_MODAL_MODE_QUERY_PARAM = 'useNonModalMode';

  filters: Filter[] = [];
  isLoading: boolean = true;
  isNonModalModeEnabled: boolean = false;

  readonly displayedColumns: string[] = ['name', 'actions'];

  private destroyed$: Subject<void> = new Subject();

  constructor(
    private filterService: FilterService,
    private matDialog: MatDialog,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.updateModalModeEnabled();
    this.refreshFilters();
  }

  ngOnDestroy() {
    this.destroyed$.complete();
  }

  private updateModalModeEnabled() {
    this.route.queryParamMap
      .pipe(takeUntil(this.destroyed$))
      .subscribe((paramMap) => {
        if (
          paramMap &&
          paramMap.has(FilterListComponent.NON_MODAL_MODE_QUERY_PARAM)
        ) {
          this.isNonModalModeEnabled =
            paramMap.get(FilterListComponent.NON_MODAL_MODE_QUERY_PARAM) ===
            'true';
        }
      });
  }

  refreshFilters() {
    this.isLoading = true;
    this.filterService
      .findAll()
      .pipe(
        first(),
        finalize(() => (this.isLoading = false)),
      )
      .subscribe((filters) => (this.filters = filters));
  }

  openDialogForm(filter: Filter | null = null) {
    this.matDialog
      .open(FilterFormComponent, {
        data: { filter, draggable: this.isNonModalModeEnabled },
        panelClass: ['resizeable-dialog', 'filter-form-dialog'],
        hasBackdrop: !this.isNonModalModeEnabled,
      })
      .afterClosed()
      .pipe(first())
      .subscribe((result) => {
        if (result) this.refreshFilters();
      });
  }
}
