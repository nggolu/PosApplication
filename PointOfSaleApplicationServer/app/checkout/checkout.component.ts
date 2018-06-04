import {Component, OnInit, Input, Output} from '@angular/core';
import {CartService} from "../cart/cart.service";
import {CheckoutService} from "./checkout.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  customerId;
  customerName;
  totalPrice = 0;
  myVar;
  cartProducts = [];

  constructor(private cartService: CartService, private checkoutService: CheckoutService,private router : Router) {
  }

  modeOfPayment;

  ngOnInit() {
    if(localStorage.getItem('token')==null){
      this.router.navigate(['login']);
    }
    if(localStorage.getItem('compingFromCart')==null){
      localStorage.removeItem('compingFromCart');
      this.router.navigate(['cart'])
    }
    this.customerId = localStorage.getItem('customerId')
    this.customerName = localStorage.getItem('customerName')
    if(this.customerId == null){
      this.router.navigate(['customer']);
    }
    this.cartService.getAllCartDetails(this.customerId).subscribe((event: any) => {
      this.cartProducts = event;
      for(let cart of this.cartProducts){
        if(cart.products.stock < cart.quantity){
          window.alert(cart.products.name + "'s qunatity can not be greater than "+ cart.products.stock);
          this.router.navigate(['cart'])
        }
      }
      for (let cc of this.cartProducts) {
        this.totalPrice = this.totalPrice + cc.quantity * cc.products.price;
      }

      console.log(this.cartProducts)
    })
  }

  saveOrder() {
    console.log();
    this.checkoutService.saveOrder(this.cartProducts).subscribe((event:any) => {
      if(event.status == 200){
        window.alert(event.status + " order is saved");
      }else{
        window.alert(event.status + " order is saved");
      }
    });
    localStorage.removeItem(this.customerName);
    localStorage.removeItem(this.customerId);
    this.router.navigate(['saveOrder']);

  }
  placeOrder(){
    // console.log(event);
    console.log(this.modeOfPayment);
    if(this.modeOfPayment==null){
      window.alert("please select Payment method")
    }
    else{
      this.checkoutService.placeOrder(this.cartProducts,this.modeOfPayment).subscribe((res:any)=>{
        console.log(res);
        if(res.status == 200){
            window.alert(" order is placed");
            this.router.navigate(['order'])
        }else{
            window.alert(res.errorMessage)
            this.router.navigate(['cart']);
        }
      })
    }


  }
  selectModeOfPayemnt(event){
    console.log();
    this.modeOfPayment = event.path[0].value;
  }

}
