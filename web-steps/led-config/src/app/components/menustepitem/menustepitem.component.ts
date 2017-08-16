import {Component, OnInit, Input, ViewEncapsulation, Output, EventEmitter} from "@angular/core";
import {ModelserviceService} from "../../services/modelservice.service";
import {ProductcodeService} from "../../services/productcode.service";
import {MdCheckboxChange} from "@angular/material";
import {Subscription} from "rxjs/Subscription";
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
import {ProductConfiguration} from "../../domain/ProductConfiguration";
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
  }

  get step() {
    return this._step;
  }

  @Input()
  currentStep: number;

  @Output()
  nextStep: EventEmitter<number> = new EventEmitter<number>();


  @Input()
  selectedModel: Model;

  filteredModels: Array<Model> = [];
  displayRelation: DisplayRelation;

  skip: boolean = false;

  checked: boolean = false;
  currentPart: Part;

  @Input()
  m: Model;


  private productcodeServiceSubscription: Subscription;

  constructor(private productcodeService: ProductcodeService, private productconfigService: ProductconfigurationService, private partService: PartService) {
    this.productcodeServiceSubscription = productcodeService.productcodeSource$.subscribe(
      res => {
        if (res.currentStep == this._step.stepindex) {
          this.reset();
        }
        console.info(res);
        this.currentStep = res.currentStep;
      });


  }

  ngOnDestroy() {
    this.productcodeServiceSubscription.unsubscribe();
  }

  ngOnInit() {
    this.reset();
  }

  onCheckBoxChange(skip) {
    console.info(skip);
    this.skip = skip;
  }

  skipThisStep(value: MdCheckboxChange) {
    //this.skip = value.checked;
    console.info(value);
    console.info(this.skip);
    if (value.checked) {

      this.selectedModel = null;


    }
    this.onInputChange(null);
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
    let modelC: ModelChosenStep = this.productconfigService.productConfiguration.getModelChosenFromStep(this.step.stepindex);
    let modl = modelC.chosenModel;
    if (modelC && modl) {


      if (this.step.type == StepType.VALUES) {
        return modl ? modl.name : '';
      }
      return '' + (this.step.modelValue ? this.step.modelValue : '') + ' mm';
    }
    return '';
  }

  filter(models: Model[]) {
    let mls: Array<Model> = [];
    let dp: DisplayRelation = new DisplayRelation();
    for (let model of models) {
      let rl = this.determineRelationState(dp, model).relationState;
      if (rl == RelationState.ALLOWED || rl == RelationState.ALLOWEDWITHWARNING || rl == RelationState.ALLOWEDWITHINFO) {
        mls.push(model);
      }
    }
    this.filteredModels = mls;

    return mls;
  }

  getTabColor(m: Model): DisplayRelation {


    this.displayRelation = this.determineRelationState(new DisplayRelation(), m);
    return this.displayRelation;
  }

  determineRelationState(displayRelation: DisplayRelation, m: Model) {
    let additional = 1;
    if (this.currentStep == 1) {
      additional = 0;
    }
    let prevModels: Array<Model> = this.productconfigService.productConfiguration.prevModels(this.currentStep + additional);

    if (prevModels.length > 0 && m) {

      let relations: Array<RelationDefinition> = Model.relatedRelations(m, prevModels);


      let allowed: boolean = false;
      let allowedWithWarning: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHWARNING) {
          allowedWithWarning = true;
          displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithWarning) {
        displayRelation.relationState = RelationState.ALLOWEDWITHWARNING;
        displayRelation.color = "orange";
        return displayRelation;
      }
      let allowedWithInfo: boolean = false;
      for (let rel of relations) {
        if (rel.relationState == RelationState.ALLOWEDWITHINFO) {
          allowedWithInfo = true;
          displayRelation.message = rel.allowedWithMessage;
          break;
        }
      }
      if (allowedWithInfo) {
        displayRelation.relationState = RelationState.ALLOWEDWITHINFO;
        displayRelation.color = "black";
        return displayRelation;
      }
      for (let rel of relations) {
        if (RelationState.ALLOWED == rel.relationState) {
          allowed = true;
          break;
        }
      }
      if (allowed) {

        displayRelation.relationState = RelationState.ALLOWED;
        displayRelation.color = "black";
        return displayRelation;
      }
      displayRelation.relationState = null;
      displayRelation.color = "red";
      return displayRelation;
    } else {
      displayRelation.relationState = RelationState.ALLOWED;
      displayRelation.color = "black";
      return displayRelation;
    }
  }

  reset() {
    this.skip = false;
    this.selectedModel = null;
  }

  prevStepItems() {

    this.productcodeService.productcodeRecall(this.step.stepindex);

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
    let modelToSet = null;
    if (this.step.models.length > 0) {
      modelToSet = this.step.models[0];
    }
    this.productconfigService.productconfigAnnouncement(this.step, modelToSet, valueN);
    this.productcodeService.productcodeAnnouncement(codeString, this.step.stepindex);
    this.nextStepProcessing();
  }

  calculateStepValue(step: StepModel) {
    if (step.stepindex == 7) {

      return this.getKeyValueModelFromStep(3, 'section');
    }
    if (step.stepindex == 8) {
      return 1;
    }
  }

  changeSelect(event: Event) {
    console.info(event);
    console.info(this.m);
    if (this.m != undefined) {
      this.selectedModel = this.m;

      this.changeSelection();
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


  public stepDiff(current) {

    return current.stepindex - this.currentStep - 1;

  }


  private changeSelection() {

    console.info(this.m);
    console.info(this.selectedModel);
    this.selectedModel = this.m;
    let productConfig = this.productconfigService.productconfigAnnouncement(this.step, this.selectedModel, null);
    let curStep = this.step.stepindex;
    if (curStep == 0) {
      curStep = 1;
    }
    this.getPart(this.selectedModel, productConfig, curStep);

    this.productcodeService.productcodeAnnouncement(this.selectedModel.code, this.step.stepindex);

    this.getTabColor(this.m);
    this.nextStepProcessing();

  }


  getModelName(m:Model){
    if (m.translations){
      if (m.translations.en)
      {return m.translations.en;}
    }
    return m.translations.nl;
  }

  getImageUrl(m: Model, currentPart: Part) {
    if (m != null && m.imageUrl != null) {
      return m.imageUrl;
    }
    if (currentPart != null && currentPart.imageUrl != null) {
      return currentPart.imageUrl;
    }
    return null;
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

  getPart(model: Model, productConfig: ProductConfiguration, currentStep: number) {
    this.partService.getPart(model, productConfig, currentStep).subscribe((res: Part) => {
        let serverresponse: Part = res;
        this.currentPart = serverresponse;
        console.info(serverresponse);
      }
      ,
      error=>console.info('error'));
  }

  private nextStepProcessing() {
    this.nextStep.emit(this.step.stepindex);
  }
}
