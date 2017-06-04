import {ModelDimension} from "./ModelDimension";
export class Model {

  id:string;
  orientation: string;
  name:string;
  code:string;
  step:number;
  properies: Array<any>;
  translations: ModelTranslation;
  dimension: ModelTranslation;
  maxDimension: ModelDimension;
  margin: ModelMargin;
  lengthForCasting: ModelDimension;
  lengthForCastingStr: string;
  leftSpace: number;
  rightSpace: number;
}
