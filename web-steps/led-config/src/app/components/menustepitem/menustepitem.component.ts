import { Component, OnInit,Input } from '@angular/core';
import {ModelserviceService} from "../../services/modelservice.service";

@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers:[ModelserviceService]
})
export class MenustepitemComponent implements OnInit {

  @Input()
  title:string;

  selectedValue: string;

  @Input()
  data:Array<string>;

  constructor() {

  }

  ngOnInit() {

  }


}
