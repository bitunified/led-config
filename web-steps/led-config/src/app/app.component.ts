import {Component, ViewEncapsulation, OnInit} from "@angular/core";
import {ModelserviceService} from "./services/modelservice.service";
import {StepsService} from "./services/steps.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [ModelserviceService,StepsService]
})
export class AppComponent implements OnInit{

  steps:Observable<StepsModel>;

  constructor(private modelService:ModelserviceService,private stepService:StepsService) {
  }

  ngOnInit() {
    this.modelService.getModels().subscribe((res:any) => {
        let serverresponse:any=res;

        console.info(serverresponse);
      }
      ,
      error=>console.info('error'));

    this.steps=this.stepService.getSteps();

  }
  data = [
    [
      {value: 'L20', viewValue: 'liniLED Aeris Profiel L20', price: 10.50, dimension: {width: 200}},
      {value: 'H20', viewValue: 'liniLED Aeris Profiel H20', price: 23.23, dimension: {width: 200}},
      {value: 'L30', viewValue: 'liniLED Aeris Profiel L30', price: 30.23, dimension: {width: 300}}
    ],
    [
      {value: 'Diffuse', viewValue: 'Diffuse', price: 16.00, dimension: {width: 100}},
      {value: 'Clear', viewValue: 'Clear', price: 15.00, dimension: {width: 100}},
      {value: 'Emtpy', viewValue: 'Empty', price: 0.00, dimension: {width: 100}}
    ],
    [
      {value: 'Deco', viewValue: 'Ledstrip Deco', price: 16.00, dimension: {width: 100}},
      {value: 'Power', viewValue: 'Ledstrip Power', price: 15.00, dimension: {width: 150}},
      {value: 'HighPower', viewValue: 'Ledstrip HP', price: 0.00, dimension: {width: 800}},
      {value: 'Photon', viewValue: 'Ledstrip Photon', price: 0.00, dimension: {width: 600}},
      {value: 'Tunnable', viewValue: 'Ledstrip Tunnable', price: 0.00, dimension: {width: 400}}
    ],
    [
      {value: 'RGB', viewValue: 'Ledstrip RGB', price: 16.00, dimension: {width: 100}},
      {value: 'Rood', viewValue: 'Ledstrip Red', price: 15.00, dimension: {width: 150}},
      {value: 'Groen', viewValue: 'Ledstrip Green', price: 0.00, dimension: {width: 800}},
      {value: 'Amber', viewValue: 'Ledstrip Amber', price: 0.00, dimension: {width: 600}},
      {value: '2400K', viewValue: 'Ledstrip 2400K', price: 0.00, dimension: {width: 400}}
    ]

  ];
  models:Array<DisplayModel> = [
    {
      title:'Step 1',
      type:'values',
      models:this.data[0]
    },
    {
      title:'Step 2',
      type:'values',
      models:this.data[1]
    },
    {
      title:'Step 3a',
      type:'values',
      models:this.data[2]
    },
    {
      title:'Step 3b',
      type:'values',
      models:this.data[3]
    },
    {
      title:'Step 4',
      type:'input',
      models:[{value:200}]
    }
  ];

}
