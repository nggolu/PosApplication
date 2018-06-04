import { Injectable } from '@angular/core';
import {HttpClient , HttpHeaders} from "@angular/common/http";

const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = 'http://localhost:8080/api/customer/?filter=';
  constructor(private http: HttpClient) { }

  getAllCustomer(filter){

    // this.httpHeader.append('Content-Type', 'application/json');
    // header.append('tokeken', 'abcd');
    // let headers = new HttpHeaders();
    // this.httpHeader.set('token', 'abcd');

    return this.http.get(this.url + filter, httpOptions);
  }

}
