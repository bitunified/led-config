import {Component, OnInit, Input, Output} from "@angular/core";

import {StepsModel} from "../../domain/StepsModel";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import {Subscription} from "rxjs/Subscription";
import {ProductConfiguration} from "../../domain/ProductConfiguration";
import {Relations} from "../../domain/relations/Relations";
import {Observable} from "rxjs/Rx";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
import {NotificationService} from "../../services/notificationservice.service";
import {ErrorNotificationState} from "../../domain/internal/ErrorNotificationState";
import {NotificationMessage} from "../../domain/internal/NotificationMessage";
import {ProductcodeService} from "../../services/productcode.service";

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css']
})
export class MenustepsComponent implements OnInit {


  @Input()
  steps: Observable<StepsModel>;

  @Input()
  relations: Observable<Relations>;

  relationsAll: Relations;

  @Input()
  maxSteps: number;

  @Output()
  isProcessing: boolean = false;

  stepsall: StepsModel;

  currentStep: number;

  private productConfigSubscription: Subscription;


  private productCondeSubscription: Subscription;

  private productconfig: ProductConfiguration;

  constructor( private productcodeService: ProductcodeService,private productConfigService: ProductconfigurationService,private notificationService:NotificationService) {
    this.productConfigSubscription = productConfigService.productconfigSource$.subscribe(
      res => {
        this.productconfig = res;
      });
    this.productconfig = productConfigService.productConfiguration;


    this.productCondeSubscription = productcodeService.productcodeSource$.subscribe(res=>{
      console.info(res);
      //this.currentStep=res.currentStep;
    });
  }

  ngOnInit() {
    this.currentStep = 0;

    const combineRelationSteps: Observable<{rels: Relations,steps: StepsModel}> = Observable.combineLatest(this.relations, this.steps, (rels: Relations, steps: StepsModel)=> {
      return {rels: rels, steps: steps};
    });
    combineRelationSteps.subscribe((combinedRelStep)=> {
      this.stepsall = combinedRelStep.steps;
      this.relationsAll = combinedRelStep.rels;
      this.evaluateRelations();
    });
  }


  nextStepItem(event) {
    console.info(event);
    console.info(this.stepsall.steps.length);
    console.info(this.currentStep);
    if (this.productconfig.containsStep(event)) {
      // if (this.currentStep < this.stepsall.steps.length) {
      //   this.currentStep++;
      //
      //   return;
      // }
      if (event == this.stepsall.steps.length) {
        console.info('finish');
        this.isProcessing=true;
        let subscriptionProductPrice = this.productConfigService.sendProductConfigToServer();
        subscriptionProductPrice.subscribe((productPrice)=> {
          let productPriceCalculated: PriceCalculation = productPrice;
          this.notificationService.notificationMessageAnnouncement(new NotificationMessage("Price calculation received",ErrorNotificationState.INFO));
          this.isProcessing=false;
        });
      }
      return;
    }
    // this.notificationService.notificationMessageAnnouncement(new NotificationMessage("Select an option to click the product-item.",ErrorNotificationState.INFO));

  }

  evaluateRelations() {
    for (let step of this.stepsall.steps) {
      for (let stepModel of step.models) {
        for (let relation of this.relationsAll.relations) {
          for (let model of relation.models) {
            if (model.uuid == stepModel.uuid) {
              stepModel.relations.push(relation);
            }
          }
        }
      }
    }
  }

  prevStep() {
    console.info('parent prev');
    //if (this.currentStep > 1) {
    //  this.currentStep--;
    //}

  }

  ngOnDestroy(){
    this.productCondeSubscription.unsubscribe();
  }


}
