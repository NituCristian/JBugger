import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/configurations/services/user.service';
import { RestUser } from 'src/app/configurations/models/user.model';
import { ActivatedRoute } from '@angular/router';
import { RestNotification } from 'src/app/configurations/models/notification.model';
import { NotificationService } from 'src/app/configurations/services/notification.service';


@Component({
    selector: 'dashboard-root',
    templateUrl: './dashboard.component.html'
})

export class DashboardComponent implements OnInit {

    public loggedUser: RestUser;
    restNotificationList: RestNotification[];
    notification: RestNotification;
    crtId: number;

    constructor(private userService: UserService, private route: ActivatedRoute, private notificationService: NotificationService,) {
        this.userService.user.subscribe(x => this.loggedUser = x);
    }

    ngOnInit(): void {
        if (!this.loggedUser) {
            this.logout();
        }
    }

    giveNotif(): void {
           if (!this.notification) {
            this.notification = { notificationId: -1, userId: -1, text: '', type: '' };
        }

        this.notificationService.getNotificationByUserId(+this.crtId).subscribe((notificationList) => {
            this.restNotificationList = notificationList;

        });
    }
    
    public logout() {
        this.userService.logout();
    }
}
