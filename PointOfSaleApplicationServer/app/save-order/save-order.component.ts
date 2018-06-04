import { Component, OnInit } from '@angular/core';
import {SaveOrderService} from "./save-order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-save-order',
  templateUrl: './save-order.component.html',
  styleUrls: ['./save-order.component.css']
})
export class SaveOrderComponent implements OnInit {

  constructor(private saveOrderService : SaveOrderService,private router : Router) { }
  savedOrders = [];
  ngOnInit() {
    if(localStorage.getItem('token')==null){
      this.router.navigate(['login']);
    }
    this.saveOrderService.getAllSaveOrder().subscribe((res : any) => {
      this.savedOrders = res;
    });
  }

}
