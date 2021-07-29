import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserComponent } from 'src/app/dashboard/user/user.component';
import { LoginComponent } from './login/login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { AppComponent } from './app.component';
import { BugComponent } from './dashboard/bug/bug.component';
import { RoleComponent } from './dashboard/role/role.component';
import { RightComponent } from './dashboard/right/right.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: 'users',
        component: UserComponent
      },
      {
        path: 'bugs',
        component: BugComponent
      },
      {
        path: 'roles',
        component: RoleComponent,
      },
      {
        path: 'rights',
        component: RightComponent
      }
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }



