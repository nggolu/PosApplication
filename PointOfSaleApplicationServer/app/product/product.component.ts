import {Component, OnInit} from '@angular/core';
import {ProductService} from "./product.service";
import {CartService} from "../cart/cart.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products = [];
  filter :any = '' ;
  constructor(private productService: ProductService, private cartService: CartService, private router: Router) {
  }

  customerId;
  customerName;

  ngOnInit() {
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['login']);
    }
    else {
      console.log(localStorage.getItem('token'))
      this.customerId = localStorage.getItem('customerId')
      this.customerName = localStorage.getItem('customerName')

      this.productService.getAllProducts().subscribe((event: any) => {
        this.products = event;
        console.log(this.products)
      })
    }

  }

  addToCart(id) {
    console.log(id)

    // window.alert('added to cart');
    if (this.customerId == null) {
      window.alert("please select customer First");
      this.router.navigate(['customer']);
    } else {

      this.cartService.addProductToCart(id, this.customerId).subscribe(res => console.log(res));
      window.alert("Product has been added");
    }

  }
  getProductByFilter(){

    console.log(this.filter)
    this.productService.getProductByFilter(this.filter).subscribe((res : any)=>{
      console.log(res);
      this.products=res;
    })
  }

}
