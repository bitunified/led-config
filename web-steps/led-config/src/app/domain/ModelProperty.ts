import {ModelPropertyValue} from "./ModelPropertyValue";

import { deserialize,deserializeAs } from 'cerialize';
export class ModelProperty {

  @deserialize
  key: string;

  @deserializeAs(ModelPropertyValue)
  value: ModelPropertyValue;


}
