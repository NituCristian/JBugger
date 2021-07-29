import { Component, OnInit, Input } from '@angular/core';
import { RestRight } from 'src/app/configurations/models/right.model';
import { RoleService } from 'src/app/configurations/services/role.service';
import { NumberValueAccessor } from '@angular/forms';
import { RoleComponent } from 'src/app/dashboard/role/role.component';
import { DashboardComponent } from '../dashboard.component';
import { UserService } from 'src/app/configurations/services/user.service';

@Component({
  selector: 'app-right',
  templateUrl: './right.component.html',
  styleUrls: ['./right.component.css']
})
export class RightComponent implements OnInit {

  hasPermissionManagementRight: boolean = false;
  restRightList: RestRight[];
  restRight: RestRight;
  roleComponent: RoleComponent;

  dashboardComponent: DashboardComponent;

  toAdd: boolean = false;
  newAttribute: any = {};

  rightId: number;
  rightName: string;

  constructor(private roleService: RoleService, private userService: UserService, private dashboard: DashboardComponent) { }

  ngOnInit(): void {
    if (!this.dashboard.loggedUser) {
      this.dashboard.logout();
    }

    this.getRights();

    this.userService.getAllRightsUser(this.dashboard.loggedUser.userId).subscribe((rightList) => {
      for (let right of rightList) {
        if (right.name == "PERMISSION_MANAGEMENT") {
          this.hasPermissionManagementRight = true;
        }
      }
    }, (error) => {
      console.log(error);
    })
  }

  addRightToRole() {
    if (!this.restRight) {
      this.restRight = { rightId: -1, name: '' };
    }
    this.restRight.name = (<HTMLInputElement>document.getElementById('newRightName')).value;

    if (this.restRight.name == "USER_MANAGEMENT") {
      this.restRight.rightId = 1;
    }

    else if (this.restRight.name == "PERMISSION_MANAGEMENT") {
      this.restRight.rightId = 2;
    }

    else if (this.restRight.name == "BUG_MANAGEMENT") {
      this.restRight.rightId = 3;
    }

    else if (this.restRight.name == "BUG_CLOSE") {
      this.restRight.rightId = 4;
    }

    else if (this.restRight.name == "BUG_EXPORT_PDF") {
      this.restRight.rightId = 5;
    }

    this.roleService.addRightToRole(this.restRight, RoleComponent.r).subscribe((restRight) => {
      this.restRight = restRight;
      alert('Right succesfully added!');
      this.getRights();
    }, (error1) => {
      console.log('Error', error1.error);
      alert('Right cannot be added.');
    });
  }

  revokeRightFromRole() {
    if (!this.restRight) {
      this.restRight = { rightId: -1, name: '' };
    }

    this.restRight.rightId = this.rightId;
    this.restRight.name = this.rightName;

    this.roleService.revokeRightFromRole(this.restRight, RoleComponent.r).subscribe((restRight) => {
      this.restRight = restRight;
      alert('Right succesfully revoked!');
      this.getRights();
    }, (error1) => {
      console.log('Error', error1.error);
      alert('Right cannot be revoked. Please enter valid data');
    });
  }

  getRights(){
        this.roleService.getAllRightsOfARole(RoleComponent.r).subscribe((rightList) => {
      this.restRightList = rightList;
    });
  }

} 
