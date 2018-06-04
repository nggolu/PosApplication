import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};
@Injectable({
  providedIn: 'root'
})
export class SaveOrderService {

  url = 'http://localhost:8080/api/orders/saveOrder';

  constructor(private http : HttpClient) { }

  getAllSaveOrder(){
    return this.http.get(this.url , httpOptions);
  }

}
