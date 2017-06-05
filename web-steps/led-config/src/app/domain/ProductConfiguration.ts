import {ModelChosenStep} from "./ModelChosenStep";
import {Model} from "./Model";
import {StepModel} from "./StepModel";
export class ProductConfiguration {

  modelsForSteps: Array<ModelChosenStep> = [];

  containsStep(step: number) {
    if (step) {
      this.modelsForSteps.forEach(function (i: ModelChosenStep) {
        if (i.step.stepindex + '' == step + '') {
          return true;
        }
      });
    }
    return false;
  }

  assignModel(step: StepModel, model: Model) {
    let found: boolean = false;
    if (this.modelsForSteps!=undefined) {
      this.modelsForSteps.forEach(function (i: ModelChosenStep) {
        if (i.step && i.step.stepindex == step.stepindex) {
          i.chosenModel = model;

          found = true;

        }
      });
      if (!found) {
        let mc = new ModelChosenStep();
        mc.chosenModel = model;
        mc.step = step;
        this.modelsForSteps.push(new ModelChosenStep)
      }
    }
  }
}
