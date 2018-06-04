import {Component, OnInit} from '@angular/core';
import {Router, RouterModule} from "@angular/router";
import {CashDrawerService} from "./cash-drawer.service";
import {computeStyle} from "@angular/animations/browser/src/util";

@Component({
  selector: 'app-cash-drawer',
  templateUrl: './cash-drawer.component.html',
  styleUrls: ['./cash-drawer.component.css']
})
export class CashDrawerComponent implements OnInit {

  constructor(private router: Router, private cashDrawerService: CashDrawerService) {
  }

  cashDrawers = [];

  ngOnInit() {
    if (localStorage.getItem('token') == null) {
      this.router.navigate(['login']);
    } else {
      // this.cashDrawerService.getCashDrawer(JSON.parse(localStorage.getItem('employee')).employees.id).subscribe((res: any) => {
      //   this.cashDrawer = res;
      //   console.log(res);
      // })

      console.log();
      this.cashDrawerService.getCashDrawer().subscribe((res : any)=> {
          this.cashDrawers = res;
          if(res == null){
            localStorage.removeItem('token');
            localStorage.removeItem('employeeName');
            localStorage.removeItem('customerName');
            localStorage.removeItem('customerId');
            this.router.navigate(['login']);
          }
          console.log(res);
      });
    }
  }
}
