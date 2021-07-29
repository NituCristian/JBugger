import { Component, OnInit, Input, Output, EventEmitter, Inject } from '@angular/core';
import { IUser, RestUser, ILogIn } from 'src/app/configurations/models/user.model';
import { UserService } from 'src/app/configurations/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { RestRight } from 'src/app/configurations/models/right.model';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { DashboardComponent } from '../dashboard.component';
import { RestRole } from 'src/app/configurations/models/role.model'
import { RoleService } from 'src/app/configurations/services/role.service';
@Component({
  selector: 'user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],

})
export class UserComponent implements OnInit {

  userEmails = new FormGroup({
    primaryEmail: new FormControl('', [
      Validators.required,
      Validators.pattern("^[a-z0-9._%+-]+@msg+\.group")])
  });
  mobNumberPattern = "^((\\+91-?)|0)?[0-9]{10}$";

  hasUserManagementRight: boolean = false;
  public userId: string;
  checkedRoles: string[];
  fieldArray: Array<any> = [];
  toAdd: boolean = false;
  newAttribute: any = {};
  restRoleList: RestRole[];
  @Input()
  user: IUser;
  email: string
  restUser: RestUser;

  @Input()
  userList: IUser[];

  restUserList: RestUser[];

  userForm: FormGroup;

  username: string;
  password: string;

  crtUserId: number;
  crtMobile: string;
  crtPass: string;
  crtEmail: string;
  newRole: RestRole;
  @Output()
  output = new EventEmitter<IUser>();

  @Output()
  deleteOutput = new EventEmitter<IUser>();

  @Output()
  val = new EventEmitter<string>();
  constructor(private route: ActivatedRoute, private userService: UserService,
    private fb: FormBuilder, private dashboard: DashboardComponent, private roleService: RoleService,) { }

  public value: string;

  get primEmail() {
    return this.userEmails.get('primaryEmail')
  }
  ngOnInit(): void {
    if (!this.dashboard.loggedUser) {
      this.dashboard.logout();

    }
    this.roleService.getAllRoles().subscribe((roleList) => {
      this.restRoleList = roleList;
    })

    this.getUsers();

    this.userService.getAllRightsUser(this.dashboard.loggedUser.userId).subscribe((rightList) => {
      for (let right of rightList) {
        if (right.name == "USER_MANAGEMENT") {
          this.hasUserManagementRight = true;
        }
      }
    }, (error) => {
      console.log(error);
    })
  }

  alertUser() {
    this.output.emit(this.user);
  }

  deleteUser() {
    this.deleteOutput.emit(this.user);
  }

  emitValue() {
    this.val.emit(this.value);
  }

  updateValue() {
    this.userService.currentValue$.next(this.value);
  }

  getUserBySpecifiedId() {
    if (!this.user) {
      this.user = { firstname: '', lastname: '', email: '', password: '' };
    }
    const id = this.route.snapshot.paramMap.get('id');
    this.userId = id;

    if (this.userId) {
      this.userService.getUserById(+this.userId).subscribe((user) => {
        this.restUser = user;
      });
    }
  }

  getUsers() {
    if (!this.user) {
      this.user = { firstname: '', lastname: '', email: '', password: '' };
    }

    this.userService.getAllUsers().subscribe((userList) => {
      this.restUserList = userList;
    })
  }

  submitUsername() {
    const iLoginObject: ILogIn = { username: this.username, password: this.password };

    this.userService.getUserById(1).subscribe((data) => {
      console.log('userById', data);
    }, (error1) => {
      console.log('Error', error1.error);
    });
    this.userService.loginUser(iLoginObject).subscribe((data) => {
      console.log('data', data);

    }, (error1) => {
      console.log('Error', error1.error);
    });
  }

  setForm(user: RestUser) {
    this.userForm = this.fb.group({
      email: [user.email], /// new FormGroup({value: user.email})
      firstname: [user.firstname],
      lastname: [user.lastname]
    });
  }

  addUser() {
    if (!this.restUser) {
      this.restUser = { firstname: '', lastname: '', email: '', mobile: '', password: '', activated: 1, userId: -1, username: '', isEditable: false, roles: [] };
    }
    this.restUser.firstname = (<HTMLInputElement>document.getElementById("newUserFirstname")).value;
    this.restUser.lastname = (<HTMLInputElement>document.getElementById("newUserLastname")).value;
    this.restUser.password = (<HTMLInputElement>document.getElementById("newUserPassword")).value;
    this.restUser.mobile = (<HTMLInputElement>document.getElementById("newUserMobile")).value;
    this.restUser.email = (<HTMLInputElement>document.getElementById("newUserEmail")).value;
    this.restUser.roles = [];

    const selected = document.querySelectorAll('#newUserRoles option:checked');
    const values = Array.from(selected).map(el => el.textContent);

    values.forEach((e, i) => {
      this.newRole = { roleId: -1, name: "" };
      this.newRole.roleId = i + 1;
      this.newRole.name = e.toUpperCase();
      this.restUser.roles.push(this.newRole);
    });

    this.userService.addUser(this.restUser).subscribe(() => {
      alert('User succesfully added!');
      this.getUsers();
    }, (error1) => {
      console.log('Error', error1.error);
    });
  }
  editUser() {
    this.userService.getUserById(+this.crtUserId).subscribe((user) => {
      this.restUser = user;
      this.restUser.mobile = this.crtMobile;
      this.restUser.password = this.crtPass;
      this.restUser.email = this.crtEmail;

      // this.restUser.roles = [];
      if (this.checkedRoles) {
        this.restUser.roles = [];
        for (let b of this.checkedRoles) {
          this.newRole = { roleId: 0, name: "" };
          this.newRole.name = b;
          this.restUser.roles.push(this.newRole);
        }
      }
      this.userService.editUser(this.restUser).subscribe(() => {
        alert('User succesfully updated!');
      }, (error1) => {
        console.log('Error', error1.error);
      });

    });
  }

  deactivateUser() {
    this.userService.getUserById(+this.crtUserId).subscribe((user) => {
      this.restUser = user;
      this.userService.deactivateUser(this.restUser, this.crtUserId).subscribe((user) => {
        console.log('user', user);
        alert('User deactivated!');
            this.getUsers();
      }, (error1) => {
        console.log('Error', error1.error);
        alert('User cannot be deactivated.');

      });
    });
  }

  activateUser() {
    this.userService.getUserById(+this.crtUserId).subscribe((user) => {
      // alert(this.user.firstname);
      this.restUser = user;
      this.userService.activateUser(this.restUser, this.crtUserId).subscribe((user) => {
        console.log('user', user);
        alert('User succesfully activated.');
        this.getUsers();

      }, (error1) => {
        console.log('Error', error1.error);
        alert('User cannot be activated.');

      });

    });
    this.getUsers();
  }
}