import {Component, ViewEncapsulation, OnInit,OnDestroy } from "@angular/core";
import {ModelserviceService} from "./services/modelservice.service";
import {StepsService} from "./services/steps.service";
import {Observable} from "rxjs/Rx";
import {ProductcodeService} from "./services/productcode.service";
import { Subscription }   from 'rxjs/Subscription';
import {StepsModel} from "./domain/StepsModel";
import {ProductCodeComposition} from "./domain/ProductCodeComposition";
import {Model} from "./domain/Model";
import {StepModel} from "./domain/StepModel";
import {ProductconfigurationService} from "./services/productconfiguration.service";
import {ProductConfiguration} from "./domain/ProductConfiguration";
import {ModelChosenStep} from "./domain/ModelChosenStep";
import {RelationService} from "./services/relationservice.service";
import {Relations} from "./domain/relations/Relations";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [RelationService,ModelserviceService,StepsService,ProductcodeService,Model,StepsModel,StepModel,ProductCodeComposition,ProductconfigurationService,ProductConfiguration,ModelChosenStep]
})
export class AppComponent implements OnInit,OnDestroy {

  steps:Observable<StepsModel>;
  productcode:string;
  subscription: Subscription;
  relations:Observable<Relations>;

  constructor(private modelService:ModelserviceService,private stepService:StepsService,productcodeService:ProductcodeService,private relationsService:RelationService) {
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
    this.relations=this.relationsService.getRelations();

  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


}
