import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FilterListComponent } from './components/filter-list/filter-list.component';
import { FilterFormComponent } from './components/filter-list/filter-form/filter-form.component';

const routes: Routes = [
  {
    path: 'filters',
    component: FilterListComponent,
    children: [
      {
        path: '{id}',
        component: FilterFormComponent,
      },
    ],
  },
  {
    path: '**',
    redirectTo: '/filters',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
