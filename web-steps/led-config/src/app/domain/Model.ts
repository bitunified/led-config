import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
import {ModelTranslation} from "./ModelTranslation";
import { serialize,deserialize,deserializeAs,serializeAs } from 'cerialize';
import {ModelMargin} from "./ModelMargin";
import {ModelPropertyValue} from "./ModelPropertyValue";
import {BaseClass} from "./BaseClass";

export class Model extends BaseClass {

  @serialize
  @deserialize
  typeClass:string;


  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margins: ModelMargin;

  @serialize
  @deserialize
  uuid:string;

  @serialize
  @deserialize
  id:string;

  @serialize
  @deserialize
  orientation: string;

  @serialize
  @deserialize
  name:string;

  @serialize
  @deserialize
  code:string;

  @serialize
  @deserialize
  step:number;

  @serializeAs(ModelPropertyValue)
  @deserializeAs(ModelPropertyValue)
  properties:Array<ModelPropertyValue>;

  @serializeAs(ModelTranslation)
  @deserializeAs(ModelTranslation)
  translations: ModelTranslation;

  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  dimension: ModelDimension;

  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  maxDimension: ModelDimension;

  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margin: ModelMargin;

  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  lengthForCasting: ModelDimension;
  lengthForCastingStr: string;

  @serialize
  @deserialize
  leftSpace: number;

  @serialize
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
