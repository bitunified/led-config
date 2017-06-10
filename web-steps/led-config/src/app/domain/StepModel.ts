
import {Model} from "./Model";

import { deserialize,deserializeAs } from 'cerialize';
import {StepType} from "./StepType";

export class StepModel {

  @deserializeAs(Model)
  description:string;

  @deserialize
  stepindex:number;

  @deserializeAs(Model)
  models:Array<Model>=[];

  @deserializeAs(StepType)
  type:StepType;

  modelValue:any;
}
