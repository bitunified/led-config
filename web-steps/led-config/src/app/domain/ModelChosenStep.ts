import {StepModel} from "./StepModel";
import {Model} from "./Model";
import { serialize,serializeAs } from 'cerialize';
export class ModelChosenStep {

  @serializeAs(StepModel)
  public step:StepModel;

  @serializeAs(Model)
  chosenModel:Model;

  @serialize
  modelValue:number;
}
