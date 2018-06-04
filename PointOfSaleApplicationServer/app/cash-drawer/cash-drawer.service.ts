import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};
@Injectable({
  providedIn: 'root'
})
export class CashDrawerService {

  url = 'http://localhost:8080/api/cashDrawer/';
  constructor(private http : HttpClient) { }

  getCashDrawer(){
    return this.http.get(this.url,httpOptions);
  }
}
