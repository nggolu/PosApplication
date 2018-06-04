import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  url='http://localhost:8080/api/orders/getOrders/';
  constructor(private http : HttpClient) { }

  getAllOders(){
    return this.http.get(this.url,httpOptions);
  }

}
