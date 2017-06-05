import {Component, OnInit, Input} from "@angular/core";
import {Observable} from "rxjs";
import {MdSnackBar} from "@angular/material";
import {NotificationComponent} from "../notification/notification.component";
import {StepsModel} from "../../domain/StepsModel";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import {Subscription} from "rxjs/Subscription";
import {ProductConfiguration} from "../../domain/ProductConfiguration";

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css']
})
export class MenustepsComponent implements OnInit {


  @Input()
  steps: Observable<StepsModel>;

  stepsall: StepsModel;

  currentStep: number;

  @Input()
  maxSteps: number;

  private productConfigSubscription: Subscription;

  private productconfig: ProductConfiguration;

  constructor(public snackBar: MdSnackBar, private productConfigService: ProductconfigurationService) {
    this.productConfigSubscription = productConfigService.productconfigSource$.subscribe(
      res => {
        this.productconfig = res;
      });
  }

  openSnackBar() {
    this.snackBar.openFromComponent(NotificationComponent, null);
  }

  nextStep() {
    if (this.productconfig.containsStep(this.currentStep)) {


      if (this.currentStep < this.stepsall.steps.length) {
        this.currentStep++;
      }
    }

  }

  prevStep() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }

  }

  ngOnInit() {
    this.currentStep = 1;

    this.steps.subscribe((res: StepsModel) => {
        this.stepsall = res;

        console.info(res);
      }
      ,
      error=>console.info('error'));


  }


}
