import {Component, Optional,ViewEncapsulation} from '@angular/core';
import {ModelserviceService} from "./services/modelservice.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation:ViewEncapsulation.None,
  providers:[ModelserviceService]
})
export class AppComponent {
  titles = ['Step 1','Step 2'];
  data=[[ {value: 'L20', viewValue: 'liniLED Aeris Profiel L20',price:10.50,dimension:{width:200}},
    {value: 'H20', viewValue: 'liniLED Aeris Profiel H20',price:23.23,dimension:{width:200}},
    {value: 'L30', viewValue: 'liniLED Aeris Profiel L30',price:30.23,dimension:{width:200}}],
    [ {value: 'Diffuse', viewValue: 'Diffuse',price:16.00,dimension:{width:100}},
    {value: 'Clear', viewValue: 'Clear',price:15.00,dimension:{width:100}},
    {value: 'Emtpy', viewValue: 'Empty',price:0.00,dimension:{width:100}}]]
}
