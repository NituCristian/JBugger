import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard.component';
import { UserComponent } from 'src/app/dashboard/user/user.component';
import { BugComponent } from './bug/bug.component';
import { RoleComponent } from './role/role.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RightComponent } from './right/right.component';
import { BackendModule } from 'src/app/backend/backend.module';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';


@NgModule({
  declarations: [DashboardComponent, UserComponent, BugComponent, RoleComponent, RightComponent], 
  imports: [
    RouterModule,
    CommonModule,
    BsDropdownModule,
    FormsModule,
    ReactiveFormsModule,
    BackendModule
  ]
})
export class DashboardModule { }
