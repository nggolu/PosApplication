import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};

@Injectable({
  providedIn: 'root'
})
export class LogoutService {


  constructor(private http : HttpClient) { }
  url = 'http://localhost:8080/api/employee/logOut/';

  logOut(price){

    return this.http.post(this.url+'?price='+price , {} , httpOptions);
  }
}
