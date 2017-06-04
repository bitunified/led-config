import {Component, OnInit, Input, ViewEncapsulation} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MdTabChangeEvent} from "@angular/material";
import {StepModel} from "../../domain/StepModel";
import {Model} from "../../domain/Model";
@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers: [ModelserviceService,StepModel],
  encapsulation: ViewEncapsulation.None
})
export class MenustepitemComponent implements OnInit {

  @Input()
  step: StepModel;

  @Input()
  currentStep: number;

  selectedModel:Model;

  constructor(private productcodeService: ProductcodeService) {

  }

  ngOnInit() {

  }

  public stepDiff(current) {

    return current.stepindex - this.currentStep;

  }

  changedTabSelection(event: MdTabChangeEvent) {
    console.info(event);

    this.selectedModel=this.step.models[event.index];
    this.productcodeService.productcodeAnnouncement(this.selectedModel.code,this.currentStep);
  }

}
