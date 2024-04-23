import {
  Component,
  EventEmitter,
  Input,
  OnDestroy,
  OnInit,
  Output,
} from '@angular/core';
import { FormGroup } from '@angular/forms';
import {
  CriteriaCondition,
  CriteriaConditionsByType,
  CriteriaType,
} from '../../../../models/filters.models';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-filter-condition-form',
  templateUrl: './filter-condition-form.component.html',
  styleUrls: ['./filter-condition-form.component.scss'],
})
export class FilterConditionFormComponent implements OnInit, OnDestroy {
  @Input() canEdit!: boolean;
  @Input() criteriaForm!: FormGroup;
  @Output() deleted: EventEmitter<void> = new EventEmitter<void>();

  readonly CriteriaTypes = Object.keys(CriteriaType);
  readonly CriteriaType = CriteriaType;

  private destroyed$: Subject<void> = new Subject<void>();

  constructor() {}

  ngOnInit() {
    this.criteriaForm
      .get('criteriaType')!!
      .valueChanges.pipe(takeUntil(this.destroyed$))
      .subscribe(() => this.handleConditionTypeChange());
  }

  ngOnDestroy() {
    this.destroyed$.complete();
  }

  handleConditionTypeChange() {
    this.criteriaForm
      ?.get('criteriaCondition')
      ?.setValue(this.criteriaConditions[0]);
    this.criteriaForm?.get('value')?.setValue(null);
    this.criteriaForm?.get('value')?.markAsUntouched();
    this.criteriaForm.updateValueAndValidity();
  }

  get criteriaType(): CriteriaType {
    return this.criteriaForm.get('criteriaType')?.value;
  }

  get criteriaConditions(): CriteriaCondition[] {
    return this.criteriaType == null ||
      !CriteriaConditionsByType.hasOwnProperty(this.criteriaType)
      ? []
      : CriteriaConditionsByType[this.criteriaType];
  }

  displayEnum(value: string): string {
    const cleanValue = value.replace(/_/g, ' ');
    return cleanValue[0].toUpperCase() + cleanValue.substring(1).toLowerCase();
  }

  emitDeleteEvent() {
    this.deleted.emit();
  }
}
