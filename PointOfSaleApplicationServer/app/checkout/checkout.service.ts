import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};
@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  url = 'http://localhost:8080/api/orders/';
  constructor(private http : HttpClient) { }

  saveOrder(cartProduct){
    return this.http.post(this.url + 'saveOrder' , cartProduct , httpOptions);
  }

  placeOrder(cartProduct, modeOfPayment){
    return this.http.post(this.url + 'placeOrder/?modeOfPayment=' + modeOfPayment , cartProduct, httpOptions);
  }
}
