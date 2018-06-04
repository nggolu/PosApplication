import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  url='http://localhost:8080/api/cart/';
  // deleteUrl= "http://localhost:8080/api/cart/?id="
 // produt_url = 'http://localhost:8080/api/cart/?id=';
  constructor(private http : HttpClient) { }

  getAllCartDetails(id){
    return this.http.get(this.url+'?id='+id);
  }

  removeOrderedProduct(id){
    console.log(id);
    return this.http.delete(this.url+'?id='+id);
  }

  deleteCart(id){
    return this.http.delete(this.url+'deleteCart/?id='+id);
  }
  addProductToCart(id,customerId){
    return this.http.post(this.url+'?id='+id,{id : customerId});

  }

  updateQuantity(quantity,id){
     return this.http.post(this.url+'changeQuantity/?quantity='+quantity,{id : id});
     // console.log(" heelo")
  }


}
