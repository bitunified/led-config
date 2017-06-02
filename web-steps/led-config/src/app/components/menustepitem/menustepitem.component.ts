import { Component, OnInit,Input ,ViewEncapsulation} from '@angular/core';
import {ModelserviceService} from "../../services/modelservice.service";

@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers:[ModelserviceService],
  encapsulation: ViewEncapsulation.None
})
export class MenustepitemComponent implements OnInit {

  @Input()
  model:DisplayModel;

  selectedValue: string;


  constructor() {

  }

  ngOnInit() {

  }


}
