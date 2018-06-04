import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LogoutService} from "./logout.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private logoutService : LogoutService, private router : Router) { }
  endingPrice;

  ngOnInit() {
    if(localStorage.getItem('token')==null){
      this.router.navigate(['login']);
    }
  }


  logout(){
    this.logoutService.logOut(this.endingPrice).subscribe((res:any)=>{
      console.log(res);
      if(res.status == 200){
        localStorage.removeItem('token');
        window.alert(res.successMessage);
        location.reload();
        this.router.navigate(['login']);
      }else {
        window.alert(res.errorMessage);
      }
    })
  }
}
