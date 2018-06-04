import {Component, OnInit, EventEmitter, Output} from '@angular/core';
import {CartService} from "./cart.service";
import {Router} from "@angular/router";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(private cartService: CartService, private router: Router) {
  }

  customerId;
  cartProducts = [];
  customerName;
  totalPrice = 0;


  ngOnInit() {
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['login']);
    }
    this.customerId = localStorage.getItem('customerId')
    this.customerName = localStorage.getItem('customerName')
    if (localStorage.getItem('customerId') == null) {
      this.router.navigate(['customer']);
    } else {
      this.cartService.getAllCartDetails(this.customerId).subscribe((event: any) => {
        this.cartProducts = event;

        // this.customerId = this.cartProducts[0].cart.customer.id;
        // this.customerName = this.cartProducts[0].cart.customer.name;
        // console.log(this.cartProducts)

        for (let cc of this.cartProducts) {
          console.log(cc.quantity)
          this.totalPrice = this.totalPrice + cc.quantity * cc.products.price;
        }
      });
    }

    // console.log(this.cartProducts)

  }

  deletecartProduct(id) {
    console.log(id);
    this.cartService.removeOrderedProduct(id).subscribe(res => console.log(res));
    let index = this.cartProducts.findIndex(item => item.id == id);
    console.log(index)
    this.totalPrice = this.totalPrice - this.cartProducts[index].quantity * this.cartProducts[index].products.price;

    this.cartProducts.splice(index, 1);

  }

  deleteCart(cartId) {
    console.log(cartId);
    this.cartService.deleteCart(cartId).subscribe((res) => console.log());
    this.cartProducts = [];
    this.totalPrice = 0;
  }

  changeQuantity(quantity, cartProductId, stock, check) {

    console.log(quantity, cartProductId, stock, check);
    if (quantity < 1 && check == -1) {
      window.alert('quantity can not be zero');
    }
    else if (quantity > stock && check == 1) {
      window.alert('quantity can not be greater than stock')
    }
    else {
      this.cartService.updateQuantity(quantity, cartProductId).subscribe((res: any) => {
        console.log(res)
      });
      let index = this.cartProducts.findIndex(item => item.id == cartProductId);
      console.log(this.cartProducts[index])
      console.log(quantity)
      this.totalPrice = this.totalPrice -
        this.cartProducts[index].quantity * this.cartProducts[index].products.price +
        quantity * this.cartProducts[index].products.price;
      this.cartProducts[index].quantity = quantity;
    }
  }

  CheckOut() {
    let flag = true;
    for (let chartProduct of this.cartProducts) {
      if (chartProduct.quantity > chartProduct.products.stock) {
        window.alert(chartProduct.products.name + ' can not be greater than ' + chartProduct.products.stock)
        // this.router.navigate(['cart'])
        flag = false;
      }
    }
    if (flag === true) {
      localStorage.setItem('compingFromCart','true');
      this.router.navigate(['checkOut'])
    }
  }

}
