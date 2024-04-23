import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  CriteriaConditionsByType,
  CriteriaType,
  Filter,
} from '../../../models/filters.models';
import { FilterService } from '../../../services/filter.service';
import { finalize, first } from 'rxjs';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from '../../../services/notification.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-filter-form',
  templateUrl: './filter-form.component.html',
  styleUrls: ['./filter-form.component.scss'],
})
export class FilterFormComponent implements OnInit {
  filterForm: FormGroup | null = null;
  isSaving: boolean = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: FilterFormInputData,
    private fb: FormBuilder,
    private filterService: FilterService,
    private notificationService: NotificationService,
    private matDialogRef: MatDialogRef<FilterFormComponent>,
    private datePipe: DatePipe,
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm() {
    this.filterForm = this.fb.group({
      name: this.fb.control(null, [Validators.required]),
      criteria: this.fb.array([], [Validators.required]),
    });

    this.constructInitialCriteria();

    if (this.data.filter) {
      this.data.filter.criteria.forEach((criteria) => {
        if (criteria.criteriaType === CriteriaType.DATE) {
          criteria.value = new Date(criteria.value).toISOString();
        }
      });

      this.filterForm.patchValue(this.data.filter);
      this.filterForm.disable();
    }
  }

  private constructInitialCriteria() {
    const criteriaCount = this.data.filter
      ? this.data.filter.criteria.length
      : 1;
    const criteriaFormArray = this.filterForm!!.get('criteria') as FormArray;

    for (let i = 0; i < criteriaCount; i++) {
      criteriaFormArray.push(this.createCriteriaFormGroup());
    }
  }

  get criteriaArray() {
    return this.filterForm?.get('criteria') as FormArray;
  }

  get canEdit(): boolean {
    return this.data.filter == null;
  }

  getCriteriaForm(index: number): FormGroup {
    return this.criteriaArray.at(index) as FormGroup;
  }

  addCriteria() {
    this.criteriaArray.push(this.createCriteriaFormGroup());
  }

  createCriteriaFormGroup(): FormGroup {
    return this.fb.group({
      criteriaType: this.fb.control(CriteriaType.AMOUNT, [Validators.required]),
      criteriaCondition: this.fb.control(
        CriteriaConditionsByType[CriteriaType.AMOUNT][0],
        [Validators.required],
      ),
      value: this.fb.control(null, [Validators.required]),
    });
  }

  removeCriteria(index: number) {
    this.criteriaArray.removeAt(index);
  }

  saveFilter() {
    if (!this.filterForm) return;

    if (this.filterForm.invalid) {
      this.filterForm.markAllAsTouched();
      this.notificationService.error(
        'The filter form contains errors',
        null,
        true,
      );
      return;
    }

    const filter = this.getFilterValue();

    this.isSaving = true;
    this.filterService
      .create(filter)
      .pipe(
        first(),
        finalize(() => (this.isSaving = false)),
      )
      .subscribe({
        next: () => {
          this.notificationService.success('Filter added');
          this.matDialogRef.close(true);
        },
        error: (e: unknown) => {
          this.notificationService.error('Failed to save filter');
          console.log(e);
        },
      });
  }

  private getFilterValue(): Filter {
    const filter = this.filterForm!!.value as Filter;

    filter.criteria.forEach((criteria) => {
      if (criteria.criteriaType === CriteriaType.DATE && criteria.value) {
        criteria.value = this.datePipe.transform(
          criteria.value,
          'MM/dd/yyyy',
        )!!;
      }
    });

    return filter;
  }
}

export type FilterFormInputData = {
  filter?: Filter;
  draggable: boolean;
};
