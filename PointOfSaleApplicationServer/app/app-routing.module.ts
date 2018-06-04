import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CartComponent} from "./cart/cart.component";
import {ProductComponent} from "./product/product.component";
import {OrderComponent} from "./order/order.component";
import {CashDrawerComponent} from "./cash-drawer/cash-drawer.component";
import {ReportsComponent} from "./reports/reports.component";
import {OrderDetailComponent} from "./order-detail/order-detail.component";
import {CustomerComponent} from "./customer/customer.component";
import {CheckoutComponent} from "./checkout/checkout.component";
import {SaveOrderComponent} from "./save-order/save-order.component";
import {LoginComponent} from "./login/login.component";
import {LogoutComponent} from "./logout/logout.component";


const routes: Routes = [
  { path: 'cart', component : CartComponent},
  { path: 'product', component : ProductComponent},
  { path: 'order', component : OrderComponent},
  { path: 'cashDrawer', component :  CashDrawerComponent},
  { path: 'report', component : ReportsComponent},
  { path: 'order/:id', component: OrderDetailComponent},
  { path: 'customer', component : CustomerComponent},
  { path: 'reports', component : ReportsComponent},
  { path: 'checkOut', component : CheckoutComponent},
  { path: 'saveOrder', component : SaveOrderComponent},
  { path: 'logout', component : LogoutComponent},
  { path: 'login', component : LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
