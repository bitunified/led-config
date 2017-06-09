import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
import {ModelTranslation} from "./ModelTranslation";
import { deserialize,deserializeAs } from 'cerialize';
import {ModelMargin} from "./ModelMargin";

export class Model {

  @deserialize
  public uuid:string;

  @deserialize
  id:string;
  @deserialize
  orientation: string;
  @deserialize
  name:string;
  @deserialize
  code:string;
  @deserialize
  step:number;

  properies: Array<any>;

  @deserializeAs(ModelTranslation)
  translations: ModelTranslation;

  @deserializeAs(ModelDimension)
  dimension: ModelDimension;

  @deserializeAs(ModelDimension)
  maxDimension: ModelDimension;

  @deserializeAs(ModelMargin)
  margin: ModelMargin;

  @deserializeAs(ModelDimension)
  lengthForCasting: ModelDimension;
  lengthForCastingStr: string;

  @deserialize
  leftSpace: number;

  @deserialize
  rightSpace: number;

  relations:Array<RelationDefinition>=[];

  static relatedRelations(m:Model,prevModels:Array<Model>):Array<RelationDefinition>{
    let relatedRelations:Array<RelationDefinition>=[];

    for (let prevModel of prevModels){
      if (prevModel && prevModel.relations) {
        for (let relation of prevModel.relations) {
          for (let rel of relation.models) {
            if (rel.uuid === m.uuid) {
              relatedRelations.push(relation);
            }
          }
        }
      }
    }

    return relatedRelations;
  }
}
