import { Component, OnInit } from '@angular/core';
import {CustomerService} from "./customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  constructor(private customerService : CustomerService , private router : Router) { }
  filter :any = '' ;
  customers : any =[];

  ngOnInit() {
    if(localStorage.getItem('token')== null){
      this.router.navigate(['login']);
    }else{
      this.getCustomer();
    }

  }

  getCustomer(){

    console.log(this.filter)
    this.customerService.getAllCustomer(this.filter).subscribe((res : any) => this.customers=res);
    console.log(this.customers)
  }
  selectCustomer(id,name){
    // console.log(id);
    // this.gloabalCustyomer = {id: any : id , name:any : name};
    localStorage.setItem('customerId',id);
    localStorage.setItem('customerName',name);
    // localStorage.setItem('customerId',id);
    // console.log(localStorage.getItem('customerId'));
    this.router.navigate(['product'])
    //window.alert(name+ ' has been selected');
  }
}
