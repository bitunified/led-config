import {ModelChosenStep} from "./ModelChosenStep";
import {Model} from "./Model";
import {StepModel} from "./StepModel";
import { serializeAs } from 'cerialize';
export class ProductConfiguration {

  @serializeAs(ModelChosenStep)
  public modelsForSteps: Array<ModelChosenStep> = [];

  containsStep(step: number) {
    let found: boolean = false;
    if (step) {
      this.modelsForSteps.forEach(function (i: ModelChosenStep) {
        if (i.step && i.step.stepindex == step) {
          found = true;
        }
      });

    }

    return found;
  }

  getModelFromStep(step: number):Model {
    if (step) {
      for(let ms of this.modelsForSteps) {

        if (ms.step && ms.step.stepindex == step) {
          return ms.chosenModel;
        }
      }

    }

    return null;
  }

  prevModels(stepMax: number) {
    let models: Array<Model> = [];
    for (let modelStep of this.modelsForSteps) {
      if (modelStep.step.stepindex < stepMax) {
        models.push(modelStep.chosenModel);
      }
    }

    return models;
  }

  assignModel(step: StepModel, model: Model, value: number) {
    let found: boolean = false;
    if (this.modelsForSteps != undefined) {
      this.modelsForSteps.forEach(function (i: ModelChosenStep) {
        if (i.step && i.step.stepindex == step.stepindex) {
          if (model != null) {
            i.chosenModel = model;
          } else {
            i.modelValue = value;
          }
          found = true;
        }
      });
      if (!found) {
        let mc = new ModelChosenStep();
        if (model != null) {
          mc.chosenModel = model;
        } else {
          mc.modelValue = value;
        }
        mc.step = step;
        this.modelsForSteps.push(mc);
      }
    }
  }

}
