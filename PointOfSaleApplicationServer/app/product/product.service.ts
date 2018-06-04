import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url='http://localhost:8080/api/products/';

  constructor(private http : HttpClient) { }

  getAllProducts(){
    return this.http.get(this.url);
  }

  getProductByFilter(filter){
    return this.http.get(this.url+'filter/?filter='+filter);
  }
}
