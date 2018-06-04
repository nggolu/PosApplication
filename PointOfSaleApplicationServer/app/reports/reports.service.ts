import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ResponseContentType} from "@angular/http";
const httpOptions ={
  headers: new HttpHeaders({'token': localStorage.getItem('token')})
};
@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  constructor(private http : HttpClient) { }
  url = 'http://localhost:8080/download/file/?fileName=';
  urls = 'http://localhost:8080/download/getFileName/?fileType=';
  generateReport(type){
    return this.http.get(this.url+type,httpOptions);
  }

  createFile(type){
    return this.http.get(this.urls+type,httpOptions);
  }

}
