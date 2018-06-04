import { Component, OnInit } from '@angular/core';
import {OrderService} from "./order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  orders = [];
  constructor(private orderService : OrderService,private router : Router) { }
  totalPrice=0;
  ngOnInit() {
    //
    if(localStorage.getItem('token') == null){
      this.router.navigate(['login']);
    }
    this.orderService.getAllOders().subscribe((event :any)=>{
      this.orders = event;


    })
  }

}
