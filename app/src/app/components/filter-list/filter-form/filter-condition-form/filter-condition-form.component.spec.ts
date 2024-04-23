import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FilterConditionFormComponent } from './filter-condition-form.component';

describe(FilterConditionFormComponent.name, () => {
  let component: FilterConditionFormComponent;
  let fixture: ComponentFixture<FilterConditionFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FilterConditionFormComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(FilterConditionFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
