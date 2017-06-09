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
import {DisplayRelation} from "../../domain/internal/DisplayRelation";
import {MdTab} from "@angular/material";
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


  @Input()
  selectedModel: Model;

  tabIndex:number;

  filteredModels:Array<Model>;
   displayRelation:DisplayRelation;

  constructor(private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService) {

  }

  ngOnInit() {

  }

  filter(models:Model[]){
    let mls:Array<Model>=[];
    for (let model of models){
      let rl=this.getTabColor(model).relationState;
      if (rl==RelationState.ALLOWED || rl==RelationState.ALLOWEDWITHWARNING){
        mls.push(model);
      }
    }
    this.filteredModels=mls;
  return mls;
  }
  getTabColor(m: Model):DisplayRelation {
    this.displayRelation=new DisplayRelation();
    let prevModels: Array<Model> = this.productconfigService.productConfiguration.prevModels(this.currentStep);

    if (prevModels.length > 0 && m) {
      let relations: Array<RelationDefinition> = Model.relatedRelations(m, prevModels);

      //console.info('m',m,'rel',relations);
      let allowed: boolean = false;
      let allowedWithWarning: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHWARNING) {
          allowedWithWarning = true;
          this.displayRelation.message=rel.allowedWithWarningMessage;
          break;
        }
      }
      if (allowedWithWarning) {
        this.displayRelation.relationState=RelationState.ALLOWEDWITHWARNING;
        this.displayRelation.color="orange";
        return this.displayRelation;
      }
      for (let rel of relations) {
        if (RelationState.ALLOWED==rel.relationState) {
          allowed = true;
        }
      }


      if (allowed) {

        this.displayRelation.relationState=RelationState.ALLOWED;
        this.displayRelation.color="black";

        return this.displayRelation;
      }
      this.displayRelation.relationState=null;
      this.displayRelation.color="red";
      return this.displayRelation;
    } else {
      this.displayRelation.relationState=RelationState.ALLOWED;
      this.displayRelation.color="black";
      return this.displayRelation;
    }
  }

  onInputChange(value: string) {
    console.info(value);
    this.step.modelValue = value;
    this.productconfigService.productconfigAnnouncement(this.step, null, Number(value));
    this.productcodeService.productcodeAnnouncement(Utils.padString(value, 4), this.currentStep);
  }


  tabClick() {

    if (!this.selectedModel) {

      this.changeTab(null);
    }
  }

  public stepDiff(current) {

    return current.stepindex - this.currentStep;

  }

  changedTabSelection(event: MdTabChangeEvent) {
    console.info(event);

    this.changeTab(event);
  }

  private changeTab(tabEvent: MdTabChangeEvent) {

    if (tabEvent){
      console.info(tabEvent);
      this.selectedModel = this.filteredModels[tabEvent.index];

      this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
      this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.currentStep);
      this.tabIndex=tabEvent.index;
    }else{
      this.selectedModel = this.filteredModels[0];
      this.tabIndex=0;
    }



  }

}
