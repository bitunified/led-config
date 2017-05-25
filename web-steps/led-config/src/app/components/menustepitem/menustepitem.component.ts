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

  data = [
    {value: 'L20', viewValue: 'liniLED Aeris Profiel L20'},
    {value: 'H20', viewValue: 'liniLED Aeris Profiel H20'},
    {value: 'L30', viewValue: 'liniLED Aeris Profiel L30'}
  ];
  constructor(modelService:ModelserviceService) {
    modelService.getModels().subscribe((res:any) => {
        let serverresponse:any=res;

        console.info(serverresponse);
      }
      ,
      error=>console.info('error'));



  }

  ngOnInit() {

  }


}
