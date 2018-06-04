import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};

@Injectable({
  providedIn: 'root'
})
export class OrderDetailService {

  url='http://localhost:8080/api/orders/';

  constructor(private http : HttpClient) {
    console.log("helo")
  }

  getOderDetails(id : any){
    // console.log(this.id)
    console.log(id)

    return this.http.get(this.url + '?id=' + id,httpOptions);
  }
  orderToCart(cardDetails){

    return this.http.post(this.url + 'orderToCart' , cardDetails);
  }

}
