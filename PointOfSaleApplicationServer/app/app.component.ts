import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  token = localStorage.getItem('token');
  name = localStorage.getItem('employeeName')
  // console.log(employee);

}

