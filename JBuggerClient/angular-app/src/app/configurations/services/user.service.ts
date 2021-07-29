import { Injectable } from '@angular/core';
import { Observable, of, Subject } from 'rxjs';
import { IUser, RestUser, ILogIn } from 'src/app/configurations/models/user.model';
import { delay, map } from 'rxjs/operators';
import { BackendService } from 'src/app/backend/backend.service';
import { BehaviorSubject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public currentValue$ = new Subject<string>();

  private userSubject: BehaviorSubject<RestUser>;
  public user: Observable<RestUser>;

  endpoint = 'http://localhost:8080/JBugger/rest/users/';
  endpointLogIn = 'http://localhost:8080/JBugger/rest/users/login';
  endpointGetUsers = 'http://localhost:8080/JBugger/rest/users/all';
  endpointUpdateUser = 'http://localhost:8080/JBugger/rest/users/update';
  endpointAddUser = 'http://localhost:8080/JBugger/rest/users/add';
  endpointDeactivateUser = 'http://localhost:8080/JBugger/rest/users/deactivate/';
  endpointActivateUser = 'http://localhost:8080/JBugger/rest/users/activate/';
  endpointRightsUser = 'http://localhost:8080/JBugger/rest/users/allRights/';

  constructor(private backendService: BackendService, private router: Router) {

    this.userSubject = new BehaviorSubject<RestUser>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  getUserById(id: number): Observable<any> {
    return this.backendService.get(this.endpoint + id);
  }

  editUser(restUser: RestUser): Observable<any> {
    return this.backendService.post(this.endpointUpdateUser, restUser);
  }

  addUser(restUser: RestUser): Observable<any> {
    return this.backendService.post(this.endpointAddUser, restUser);
  }

  deactivateUser(restUser: RestUser, idD: number): Observable<any> {
    return this.backendService.post(this.endpointDeactivateUser + idD, restUser);
  }

  activateUser(restUser: RestUser, idD: number): Observable<any> {
    return this.backendService.post(this.endpointActivateUser + idD, restUser);
  }

  loginUser(iLogIn: ILogIn): Observable<any> {
    return this.backendService.post(this.endpointLogIn, iLogIn)
      .pipe(map(user => {
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      }));
  }

  logout() {
    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/login']);
  }

  getAllUsers(): Observable<any> {
    return this.backendService.get(this.endpointGetUsers);
  }

  getAllRightsUser(userId: number): Observable<any> {
    return this.backendService.get(this.endpointRightsUser + userId);
  }
}
