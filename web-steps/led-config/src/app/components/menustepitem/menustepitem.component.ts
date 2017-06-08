import {Component, OnInit, Input, ViewEncapsulation} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MdTabChangeEvent} from "@angular/material";
import {StepModel} from "../../domain/StepModel";
import {Model} from "../../domain/Model";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import Utils from "../../utils/Utils";
import {RelationDefinition} from "../../domain/relations/RelationDefinition";
import {RelationState} from "../../domain/relations/RelationState";
@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers: [ModelserviceService, StepModel],
  encapsulation: ViewEncapsulation.None
})
export class MenustepitemComponent implements OnInit {

  @Input()
  step: StepModel;

  @Input()
  currentStep: number;


  selectedModel: Model;

  constructor(private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService) {

  }

  ngOnInit() {

  }

  getTabColor(model:Model){
    let prevModels=this.productconfigService.productConfiguration.prevModels(this.currentStep);
    console.info(model);
    let relations:Array<RelationDefinition>=model.getRelatedRelations(prevModels);
    let allowed:boolean=false;
    let allowedWithWarning:boolean=false;
    for (let rel of relations){
      if (rel.relationState==RelationState.ALLOWED){
        allowed=true;
      }
    }

    if (allowed){
      return "green";
    }
    for (let rel of relations){
      if (rel.relationState==RelationState.ALLOWEDWITHWARNING){
        allowedWithWarning=true;
      }
    }
    if (allowedWithWarning){
      return "orange";
    }
    return "red";
  }

  onInputChange(value: string) {
    console.info(value);
    this.step.modelValue = value;
    this.productconfigService.productconfigAnnouncement(this.step, null, Number(value));
    this.productcodeService.productcodeAnnouncement(Utils.padString(value, 4), this.currentStep);
  }


  tabClick() {

    if (!this.selectedModel) {

      this.changeTab(0);
    }
  }

  public stepDiff(current) {

    return current.stepindex - this.currentStep;

  }

  changedTabSelection(event: MdTabChangeEvent) {
    console.info(event);

    this.changeTab(event.index);
  }

  private changeTab(index: number) {
    this.selectedModel = this.step.models[index];
    this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
    this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.currentStep);
  }

}
