import { Injectable } from '@angular/core';
import {HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url = 'http://localhost:8080/api/employee/login';
  constructor(private http : HttpClient) { }

  validateUser(username, password,price){
    return this.http.post(this.url + '?price=' + price,{"username" : username,"password" : password});

  }
}
