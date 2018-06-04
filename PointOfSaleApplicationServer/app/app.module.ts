import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpModule } from '@angular/http';
import {HttpClientModule } from '@angular/common/http';
import {RouterModule , Routes} from "@angular/router";
import {FormsModule} from "@angular/forms";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProductComponent } from './product/product.component';
import { CartComponent } from './cart/cart.component';
import { OrderComponent } from './order/order.component';
import { CashDrawerComponent } from './cash-drawer/cash-drawer.component';
import { ReportsComponent } from './reports/reports.component';
import { OrderDetailComponent } from './order-detail/order-detail.component';
import { CustomerComponent } from './customer/customer.component' ;
import { CheckoutComponent } from './checkout/checkout.component';
import { SaveOrderComponent } from './save-order/save-order.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    CartComponent,

    OrderComponent,
    CashDrawerComponent,
    ReportsComponent,
    OrderDetailComponent,
    CustomerComponent,
    CheckoutComponent,
    SaveOrderComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
