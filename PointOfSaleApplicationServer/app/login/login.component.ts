import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from "./login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, private loginService: LoginService) {
  }

  username = '';
  password = '';
  startingPrice = '';
  employee: any = "";

  ngOnInit() {
    if (localStorage.getItem('token') != null) {
      this.router.navigate(['']);
    }
  }

  validateUser() {
    console.log(this.username, this.password, this.startingPrice);
    if (this.username == '' || this.password == '' || this.startingPrice == '') {
      window.alert("please fill all the entries")
    } else {
      this.loginService.validateUser(this.username, this.password, this.startingPrice).subscribe((res: any) => {
        if (res.status == 200) {
          this.employee = res;
          console.log(res);
          localStorage.setItem('token', JSON.stringify(res.token))
          localStorage.setItem('employeeName', this.username);
          console.log(JSON.parse(localStorage.getItem('employee')));
          location.reload();
          this.router.navigate(['product'])
        } else {
          console.log(res.errorMessage, res.status)
          window.alert("please write correct credentials");
        }
      });
    }

  }
}
