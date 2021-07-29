import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { BackendService } from 'src/app/backend/backend.service';


@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  endpointGetNotificationsByUserId = 'http://localhost:8080/JBugger/rest/notifications/user/';

  getNotificationByUserId(id: number): Observable<any> {
    return this.backendService.get(this.endpointGetNotificationsByUserId + id);
  }

  constructor(private backendService: BackendService) { }

}
