import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { RestBug } from 'src/app/configurations/models/bug.model';
import { delay } from 'rxjs/operators';
import { BackendService } from 'src/app/backend/backend.service';

@Injectable({
  providedIn: 'root'
})
export class BugService {

  endpoint = 'http://localhost:8080/JBugger/rest/bugs/'
  endpointGetBugs = 'http://localhost:8080/JBugger/rest/bugs/all';
  endpointUpdateBug = 'http://localhost:8080/JBugger/rest/bugs/update';
  endpointAddBug = 'http://localhost:8080/JBugger/rest/bugs/add';

  getBugById(id: number): Observable<any> {
    return this.backendService.get(this.endpoint + id);
  }

  constructor(private backendService: BackendService) { }

  getAllBugs(): Observable<any> {
    return this.backendService.get(this.endpointGetBugs);
  }

  editBug(restBug: RestBug): Observable<any> {
    return this.backendService.post(this.endpointUpdateBug, restBug);
  }

  addBug(restBug: RestBug): Observable<any> {
    return this.backendService.post(this.endpointAddBug, restBug);
  }

}
