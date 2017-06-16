import {Component, OnInit, Input, ViewEncapsulation} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MdTabChangeEvent,MdCheckboxChange} from "@angular/material";
import {StepModel} from "../../domain/StepModel";
import {Model} from "../../domain/Model";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import Utils from "../../utils/Utils";
import {RelationDefinition} from "../../domain/relations/RelationDefinition";
import {RelationState} from "../../domain/relations/RelationState";
import {DisplayRelation} from "../../domain/internal/DisplayRelation";
import {StepType} from "../../domain/StepType";
@Component({
  selector: 'menustepitem',
  templateUrl: './menustepitem.component.html',
  styleUrls: ['./menustepitem.component.css'],
  providers: [ModelserviceService, StepModel],
  encapsulation: ViewEncapsulation.None
})
export class MenustepitemComponent implements OnInit {

  _step: StepModel;

  @Input()
  set step(step: StepModel) {
    this._step = step;
    this.filter(step.models);
  }

  get step() {
    return this._step;
  }

  @Input()
  currentStep: number;


  @Input()
  selectedModel: Model;

  tabIndex: number;

  filteredModels: Array<Model> = [];
  displayRelation: DisplayRelation;

  skip: boolean;

  constructor(private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService) {

  }

  ngOnInit() {
  }

  skipThisStep(value:MdCheckboxChange) {
    console.info(value);
    this.skip=value.checked;
      console.info(this.skip);
    if (value.checked) {

      this.selectedModel = null;
      this.onInputChange(null);

    }

  }

  isStepTypeValues(step: StepModel) {
    if (step.type == StepType.VALUES) {
      return true;
    }
    return false;
  }

  isStepTypeNumber(step: StepModel) {
    if (step.type == StepType.NUMBER) {
      return true;
    }
    return false;
  }

  getModelTitle(): string {
    if (this.step.type == StepType.VALUES) {
      return this.selectedModel ? this.selectedModel.name : '';
    }
    return '' + (this.step.modelValue ? this.step.modelValue : '') + ' mm';
  }

  filter(models: Model[]) {
    let mls: Array<Model> = [];
    for (let model of models) {
      let rl = this.getTabColor(model).relationState;
      if (rl == RelationState.ALLOWED || rl == RelationState.ALLOWEDWITHWARNING || rl == RelationState.ALLOWEDWITHINFO) {
        mls.push(model);
      }
    }
    this.filteredModels = mls;
    return mls;
  }

  getTabColor(m: Model): DisplayRelation {
    this.displayRelation = new DisplayRelation();
    let prevModels: Array<Model> = this.productconfigService.productConfiguration.prevModels(this.currentStep);

    if (prevModels.length > 0 && m) {
      let relations: Array<RelationDefinition> = Model.relatedRelations(m, prevModels);

      //console.info('m',m,'rel',relations);
      let allowed: boolean = false;
      let allowedWithWarning: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHWARNING) {
          allowedWithWarning = true;
          this.displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithWarning) {
        this.displayRelation.relationState = RelationState.ALLOWEDWITHWARNING;
        this.displayRelation.color = "orange";
        return this.displayRelation;
      }
      let allowedWithInfo: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHINFO) {
          allowedWithInfo = true;
          this.displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithInfo) {
        this.displayRelation.relationState = RelationState.ALLOWEDWITHINFO;
        this.displayRelation.color = "black";
        return this.displayRelation;
      }
      for (let rel of relations) {
        if (RelationState.ALLOWED == rel.relationState) {
          allowed = true;
        }
      }


      if (allowed) {

        this.displayRelation.relationState = RelationState.ALLOWED;
        this.displayRelation.color = "black";

        return this.displayRelation;
      }
      this.displayRelation.relationState = null;
      this.displayRelation.color = "red";
      return this.displayRelation;
    } else {
      this.displayRelation.relationState = RelationState.ALLOWED;
      this.displayRelation.color = "black";
      return this.displayRelation;
    }
  }

  onInputChange(value: string) {
    console.info(value);
    this.step.modelValue = value;
    let valueN=null;
    let valueS=null;
    let codeString='';
    if (value) {
     valueN=Number(value);
      valueS=value;
      codeString=Utils.padString(valueS, 4);
    }
    this.productconfigService.productconfigAnnouncement(this.step, null, valueN);
    this.productcodeService.productcodeAnnouncement(codeString, this.currentStep);

  }


  tabClick() {

    console.info(this.selectedModel);
    if (this.selectedModel == undefined) {

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
    console.info(this.filteredModels);
    if (tabEvent) {

      this.selectedModel = this.filteredModels[tabEvent.index];


      this.tabIndex = tabEvent.index;
    } else {
      this.selectedModel = this.filteredModels[0];
      this.tabIndex = 0;
    }

    this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
    this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.currentStep);

  }

  getKeyValueModelFromStep(stepIndex: number, propkey: string): string {
    let model: Model = this.productconfigService.productConfiguration.getModelFromStep(stepIndex);

    if (model.properties) {
      for (let prop of model.properties) {

        if (prop.name == propkey) {
          return prop.value;
        }

      }
    }
  }


}
