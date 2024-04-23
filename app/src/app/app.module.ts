import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FilterListComponent } from './components/filter-list/filter-list.component';
import { FilterFormComponent } from './components/filter-list/filter-form/filter-form.component';
import { FilterConditionFormComponent } from './components/filter-list/filter-form/filter-condition-form/filter-condition-form.component';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { FilterService } from './services/filter.service';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NotificationService } from './services/notification.service';
import { MatNativeDateModule } from '@angular/material/core';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    FilterListComponent,
    FilterFormComponent,
    FilterConditionFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatCardModule,
    MatButtonModule,
    MatTableModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatSnackBarModule,
    DragDropModule,
  ],
  providers: [
    FilterService,
    NotificationService,
    MatNativeDateModule,
    DatePipe,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
