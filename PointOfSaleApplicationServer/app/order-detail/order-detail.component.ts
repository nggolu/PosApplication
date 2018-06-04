import { Component, OnInit } from '@angular/core';
import {OrderDetailService} from "./order-detail.service";
import {ActivatedRoute} from "@angular/router";
import {Router} from "@angular/router";

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  orderId
  orderDetails=[];
  constructor(private orderDetailService : OrderDetailService, private route : ActivatedRoute , private router : Router) {
    // console.log(this.orderid)
    this.route.params.subscribe((res:any) => this.orderId=res.id);
  }
  totalPrice=0;
  ngOnInit() {
    if(localStorage.getItem('token')==null){
      this.router.navigate(['login']);
    } else {
      this.orderDetailService.getOderDetails(this.orderId).subscribe((event: any) => {
        this.orderDetails = event;
        for (let cc of this.orderDetails) {
          this.totalPrice = this.totalPrice + cc.quantity * cc.products.price;
        }
        console.log(this.orderDetails)
      })
    }
  }

  reloadToCart(){
    console.log(this.orderDetails);
    this.orderDetailService.orderToCart(this.orderDetails).subscribe(( event : any) => {
      console.log(event);
      this.router.navigate(['saveOrder']);
    })
  }


}
