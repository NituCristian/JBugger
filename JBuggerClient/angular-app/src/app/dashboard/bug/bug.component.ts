import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RestBug } from 'src/app/configurations/models/bug.model';
import { BugService } from 'src/app/configurations/services/bug.service';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserService } from 'src/app/configurations/services/user.service';
import { DashboardComponent } from 'src/app/dashboard/dashboard.component';
import { RestUser } from 'src/app/configurations/models/user.model';

@Component({
  selector: 'app-bug',
  templateUrl: './bug.component.html',
  styleUrls: ['./bug.component.css']
})
export class BugComponent implements OnInit {

  linkAttachement: string;
  userAssignedTo: RestUser;
  restUserList: RestUser[];
  loggedUser: RestUser;
  hasBugManagementRight: boolean = false;
  hasBugCloseRight: boolean = false;
  newAttribute: any = {};
  fieldArray: Array<any> = [];
  toAdd: boolean = false;
  filteredBugs: RestBug[];

  restBugList: RestBug[];
  restBug: RestBug;

  userForm: FormGroup;

  crtId: number;

  crtTitle: string;
  crtDesc: string;
  crtVersion: string;
  crtFixed: string;
  crtSeverity: string;
  crtStatus: string;
  crtAssigned: number;
  fileString: string;

  _listFilter: string;
  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredBugs = this.listFilter ? this.performFilter(this.listFilter) : this.restBugList;
  }

  performFilter(filterBy: string): RestBug[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.restBugList.filter((bug: RestBug) =>
      bug.title.toLocaleLowerCase().indexOf(filterBy) !== -1);
  }

  constructor(private route: ActivatedRoute, private bugService: BugService,
    private userService: UserService, private dashboard: DashboardComponent) {
    this.loggedUser = this.dashboard.loggedUser;
  }

  ngOnInit(): void {
    if (!this.dashboard.loggedUser) {
      this.dashboard.logout();
    }
    this.getBugs();
    this.getUsers();
    this.userService.getAllRightsUser(this.dashboard.loggedUser.userId).subscribe((rightList) => {
      for (let right of rightList) {
        if (right.name == "BUG_MANAGEMENT") {
          this.hasBugManagementRight = true;
        }
        if (right.name == "BUG_CLOSE") {
          this.hasBugCloseRight = true;
        }
      }
    }, (error) => {
      console.log(error);
    })
  }

  addBug() {
    if (!this.restBug) {
      this.restBug = {
        bugId: -1, title: '', description: '', version: '', fixedInVersion: '',createdByUsername:'',
        targetDate: new Date().toJSON().slice(0, 10), severity: '', createdBy: -1, status: 'new', assignedTo: -1, attachment: '', isEditable: false
      };
    }
    this.restBug.createdBy = this.loggedUser.userId;
    this.restBug.title = (<HTMLInputElement>document.getElementById("newBugTitle")).value;
    this.restBug.description = (<HTMLInputElement>document.getElementById("newBugDesc")).value;
    this.restBug.version = (<HTMLInputElement>document.getElementById("newBugVersion")).value;
    this.restBug.fixedInVersion = (<HTMLInputElement>document.getElementById("newBugFixed")).value;
    this.restBug.severity = (<HTMLInputElement>document.getElementById("newBugSeverity")).value;
    this.restBug.assignedTo = Number((<HTMLInputElement>document.getElementById("newBugAssigned")).value);
    this.restBug.attachment = this.fileString;

    this.bugService.addBug(this.restBug).subscribe(() => {

    }, (error1) => {
      console.log('Error', error1.error);
      alert('Bug cannot be added. Please enter valid data.');
    });
    this.toAdd = false;
  }

  editBug() {
    // alert(this.crtId);

    this.bugService.getBugById(+this.crtId).subscribe((bug) => {
      this.restBug = bug;
      this.restBug.title = this.crtTitle;
      this.restBug.description = this.crtDesc;
      this.restBug.version = this.crtVersion;
      this.restBug.fixedInVersion = this.crtFixed;
      this.restBug.severity = this.crtSeverity;
      this.restBug.status = this.crtStatus;
      this.restBug.assignedTo = this.crtAssigned;
      this.bugService.editBug(this.restBug).subscribe(() => {
      }, (error1) => {
        console.log('Error', error1.error);
        alert('Bug cannot be updated. Please enter valid data');
      });
    });
  }
  changeFile(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = error => reject(error);
    });
  }
  uploadFile(event): string {
    if (event.target.value) {
      const file = event.target.files[0];
      const type = file.type;
      this.changeFile(file).then((base64: string): any => {
        console.log(base64);
        this.linkAttachement = base64;
        this.fileString = base64;
      });
      return this.fileString;
    }
    else alert('Nothing');
  }

  getBugs() {
    this.bugService.getAllBugs().subscribe((bugList) => {
      this.restBugList = bugList;
      this.filteredBugs = bugList;
      for (let b of this.restBugList) {
        this.userService.getUserById(b.createdBy).subscribe((user) => {
          b.createdByUsername = user.username;
        })
      }
    })
  }

  getUsers() {
    this.userService.getAllUsers().subscribe((userList) => {
      this.restUserList = userList;
    })
  }
}
