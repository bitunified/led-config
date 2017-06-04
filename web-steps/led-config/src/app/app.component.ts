import {Component, ViewEncapsulation, OnInit,OnDestroy } from "@angular/core";
import {ModelserviceService} from "./services/modelservice.service";
import {StepsService} from "./services/steps.service";
import {Observable} from "rxjs";
import {ProductcodeService} from "./services/productcode.service";
import { Subscription }   from 'rxjs/Subscription';
import {StepsModel} from "./domain/StepsModel";
import {ProductCodeComposition} from "./domain/ProductCodeComposition";
import {Model} from "./domain/Model";
import {StepModel} from "./domain/StepModel";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [ModelserviceService,StepsService,ProductcodeService,Model,StepsModel,StepModel,ProductCodeComposition]
})
export class AppComponent implements OnInit,OnDestroy {

  steps:Observable<StepsModel>;

  productcode:string;
  subscription: Subscription;

  constructor(private modelService:ModelserviceService,private stepService:StepsService,productcodeService:ProductcodeService) {
    this.subscription = productcodeService.productcodeSource$.subscribe(
      res => {
        this.productcode = res.getCode();
      });
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
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}
