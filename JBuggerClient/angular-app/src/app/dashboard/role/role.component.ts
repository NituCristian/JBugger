import { Component, OnInit } from '@angular/core';
import { RoleService } from 'src/app/configurations/services/role.service';
import {RestRole} from 'src/app/configurations/models/role.model';
import { DashboardComponent } from '../dashboard.component';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-role',
  templateUrl: './role.component.html',
  styleUrls: ['./role.component.css']
})
export class RoleComponent implements OnInit {

  restRoleList: RestRole[];
  roleId: number;

  public static r: number;

  constructor(private roleService: RoleService, private dashboard: DashboardComponent, private router: Router) { }


  ngOnInit(): void {
    if (!this.dashboard.loggedUser) {
      this.dashboard.logout();
    }

    this.roleService.getAllRoles().subscribe((roleList) => {
      this.restRoleList = roleList;
    })
  }

  setRightAccess(): void {
    RoleComponent.r = this.roleId;
  }

  public getRoleId(): number{
    return this.roleId;
  }

  goToRights(): void{
    this.router.navigate(["dashboard/rights"]);
  }
}
   