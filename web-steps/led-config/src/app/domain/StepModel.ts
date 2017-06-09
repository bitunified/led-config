
import {Model} from "./Model";

import { deserialize,deserializeAs } from 'cerialize';

export class StepModel {

  @deserializeAs(Model)
  description:string;

  @deserialize
  stepindex:number;

  @deserializeAs(Model)
  models:Array<Model>=[];

  modelValue:any;
}
