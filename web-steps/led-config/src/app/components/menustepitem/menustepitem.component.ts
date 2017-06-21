import {Component, OnInit, Input, ViewEncapsulation} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MdTabChangeEvent, MdCheckboxChange} from "@angular/material";
import {StepModel} from "../../domain/StepModel";
import {Model} from "../../domain/Model";
import {ProductconfigurationService} from "../../services/productconfiguration.service";
import Utils from "../../utils/Utils";
import {RelationDefinition} from "../../domain/relations/RelationDefinition";
import {RelationState} from "../../domain/relations/RelationState";
import {DisplayRelation} from "../../domain/internal/DisplayRelation";
import {StepType} from "../../domain/StepType";
import {ModelChosenStep} from "../../domain/ModelChosenStep";
import {PartService} from "../../services/partservice.service";
import {Part} from "../../domain/server/Part";
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

  currentPart: Part;

  constructor(private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService, private partService: PartService) {

  }

  ngOnInit() {
  }

  skipThisStep(value: MdCheckboxChange) {
    console.info(value);
    this.skip = value.checked;
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
    let valueN = null;
    let valueS = null;
    let codeString = '';
    if (value) {
      valueN = Number(value);
      valueS = value;
      codeString = Utils.padString(valueS, 4);
    }
    let modelToSet=null;
    if (this.step.models.length>0){
      modelToSet=this.step.models[0];
    }
    console.info(modelToSet);
    this.productconfigService.productconfigAnnouncement(this.step, modelToSet, valueN);
    this.productcodeService.productcodeAnnouncement(codeString, this.currentStep);

  }

  calculateStepValue(step: StepModel) {
    if (step.stepindex == 7) {

      return this.getKeyValueModelFromStep(3, 'section');
    }
    if (step.stepindex == 8) {
      return 1;
    }
  }

  calculateMinValue(step: StepModel) {
    if (step.stepindex == 7) {

      return this.calculateStepValue(step);
    }
    let totalMinLength = 100;
    if (step.stepindex == 8) {
      let model: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(7);
      let modelMountingEndCaps: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(6);
      let ledstripLength: number = Number(model.modelValue);
      let totalMargins: number = 0;
      if (modelMountingEndCaps.chosenModel.margins) {
        totalMargins = modelMountingEndCaps.chosenModel.margins.left + modelMountingEndCaps.chosenModel.margins.right;
      }
      totalMinLength = ledstripLength + Number(totalMargins);
    }
    return totalMinLength;
  }

  tabClick() {

    if (this.selectedModel == undefined) {

      this.changeTab(null);
    }
  }

  public stepDiff(current) {

    return current.stepindex - this.currentStep;

  }

  changedTabSelection(event: MdTabChangeEvent) {


    this.changeTab(event);
  }

  private changeTab(tabEvent: MdTabChangeEvent) {

    if (tabEvent) {

      this.selectedModel = this.filteredModels[tabEvent.index];


      this.tabIndex = tabEvent.index;
    } else {
      this.selectedModel = this.filteredModels[0];
      this.tabIndex = 0;
    }

    this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
    this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.currentStep);
    this.getPart(this.selectedModel);
  }

  getKeyValueModelFromStep(stepIndex: number, propkey: string): string {
    let model: Model = this.productconfigService.productConfiguration.getModelFromStep(stepIndex);

    if (model) {
      if (model.properties) {
        for (let prop of model.properties) {

          if (prop.name == propkey) {
            return prop.value;
          }

        }
      }
    }
  }

  getPart(model: Model) {
     this.partService.getPart(model).subscribe((res:Part) => {
      let serverresponse:Part=res;
      this.currentPart=serverresponse;
      console.info(serverresponse);
    }
  ,
    error=>console.info('error'));
  }

}
