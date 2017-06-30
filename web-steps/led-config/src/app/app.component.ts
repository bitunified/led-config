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
import {ModelDimension} from "./domain/ModelDimension";
import {ModelMargin} from "./domain/ModelMargin";
import {ModelTranslation} from "./domain/ModelTranslation";
import {PriceCalculation} from "./domain/server/PriceCalculation";
import {NotificationService} from "./services/notificationservice.service";
import {NotificationComponent} from "./components/notification/notification.component";
import {MdSnackBar} from "@angular/material";
import {ErrorNotificationState} from "./domain/internal/ErrorNotificationState";
import {PartService} from "./services/partservice.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    PartService,NotificationComponent,NotificationService,ModelMargin,ModelDimension,ModelTranslation,RelationService,ModelserviceService,StepsService,ProductcodeService,Model,StepsModel,StepModel,ProductCodeComposition,ProductconfigurationService,ProductConfiguration,ModelChosenStep]
})
export class AppComponent implements OnInit,OnDestroy {

  steps:Observable<StepsModel>;
  productcode:string;
  subscription: Subscription;
  subscriptionPriceCalcution: Subscription;
  subscriptionNotificationMessage:Subscription;
  relations:Observable<Relations>;
  priceCalculation:PriceCalculation;
  notificationMessage:string;
  constructor(public snackBar: MdSnackBar,private notificationMessageService:NotificationService,private modelService:ModelserviceService,private stepService:StepsService,productcodeService:ProductcodeService,private relationsService:RelationService,private productConfiguration:ProductconfigurationService) {
    this.subscription = productcodeService.productcodeSource$.subscribe(
      res => {
        this.productcode = res.getCode();
      });

    this.subscriptionPriceCalcution = productConfiguration.priceCalcSource$.subscribe(
      res => {
        this.priceCalculation=res;
      });

    this.subscriptionNotificationMessage = notificationMessageService.notificationMessageSource$.subscribe(
      res => {
        this.notificationMessage = res.message;
        let duration:number=3000;
        if (res.state==ErrorNotificationState.ERROR){
          duration=15000;
        }
        this.snackBar.open(res.message, 'Close',{duration:duration});
        console.info(res);
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
    this.subscriptionPriceCalcution.unsubscribe();
  }

  backToConfig(){
    this.priceCalculation=null;
    window.location.reload();
  }

}
