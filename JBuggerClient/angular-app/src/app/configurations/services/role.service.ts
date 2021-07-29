import { Injectable } from '@angular/core';
import {Observable, of, Subject} from 'rxjs';
import { RestRole } from 'src/app/configurations/models/role.model';
import { RestRight } from 'src/app/configurations/models/right.model';
import { delay } from 'rxjs/operators';
import { BackendService } from 'src/app/backend/backend.service';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  endpointGetRoles = 'http://localhost:8080/JBugger/rest/roles/all';
  endpointGetRights = 'http://localhost:8080/JBugger/rest/roles/allRights/';
  endpointAddRightToRole = 'http://localhost:8080/JBugger/rest/roles/addRight/'
  endpointRevokeRightFromRole = 'http://localhost:8080/JBugger/rest/roles/revokeRight/'

  constructor(private backendService: BackendService) { }

   getAllRoles() : Observable<any>{
      return this.backendService.get(this.endpointGetRoles);
  }

  getAllRightsOfARole(roleId: number) : Observable<any>{
      return this.backendService.get(this.endpointGetRights + roleId);
  }

  addRightToRole(restRight: RestRight, roleId: number): Observable<any> {
    return this.backendService.post(this.endpointAddRightToRole + roleId, restRight);
  }

  revokeRightFromRole(restRight: RestRight, roleId: number): Observable<any> {
    return this.backendService.post(this.endpointRevokeRightFromRole + roleId, restRight);
  }

}
