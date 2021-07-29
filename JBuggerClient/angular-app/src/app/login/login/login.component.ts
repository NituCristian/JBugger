import { Component, OnInit } from '@angular/core';
import { ILogIn } from 'src/app/configurations/models/user.model';
import { UserService } from 'src/app/configurations/services/user.service';
import { FormGroup } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})


export class LoginComponent implements OnInit {
  username: string;
  password: string;
  userForm: FormGroup;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }

  submit() {
    const iLoginObject: ILogIn = { username: this.username, password: this.password };

    this.userService.loginUser(iLoginObject).subscribe((data) => {
      console.log('data', data);
      this.router.navigate(["dashboard/bugs"]);
    }, (error1) => {
      alert("Wrong credentials!");
      console.log('Error', error1.error);
    });
  }

}
