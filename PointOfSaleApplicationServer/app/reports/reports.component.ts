import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ReportsService} from "./reports.service";
import {HttpClient, HttpEvent, HttpEventType, HttpRequest} from "@angular/common/http";
import {delay} from "q";

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  constructor(private router : Router,private reportsService : ReportsService,private http:HttpClient) { }
  report ;
  ngOnInit() {
    if(localStorage.getItem('token')==null){

      this.router.navigate(['login']);
    }
  }
  flag=0;
  path='';
  percentageDownloaded;
  generateReport() {
    // console.log(event);
    console.log(this.report);
    if (this.report == null) {
      window.alert("please select report you want to denerate")
    }
    else {
        // this.reportsService.generateReport(this.report).subscribe((res :any)=>{})
        this.reportsService.createFile(this.report).subscribe((res : any)=>{
          // this.re
          console.log(res.successMessage)
          console.log("path")
          if(res != null){
            if(res.status ==200) {
              this.path = res.successMessage;
              this.reportsService.generateReport(res.successMessage).subscribe((res) => {
              })
            }
          }

        })
      // if(this.flag==1)

      // setTimeout(FunctionName, 5000);

      setTimeout( () => {

        window.open('http://localhost:8080/download/file/?fileName='+this.path,'_blank','');
        location.reload();
      }, 2000 );

    }
  }

  // downloadFile(data: Response){
  //   // console.log(Response)
  //   var blob = new Blob([data], { type: 'text/csv' });
  //   var url= window.URL.createObjectURL(blob);
  //   window.open(url);
  // }
  selectsReposrt(event){
    console.log();
    this.report = event.path[0].value;
  }

}
