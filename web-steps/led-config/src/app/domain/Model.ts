import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
import {ModelTranslation} from "./ModelTranslation";
import {serialize, deserialize, deserializeAs, serializeAs} from "cerialize";
import {ModelMargin} from "./ModelMargin";
import {ModelPropertyValue} from "./ModelPropertyValue";
import {BaseClass} from "./BaseClass";
import {RelationState} from "./relations/RelationState";

export class Model extends BaseClass {

  @serialize
  @deserialize
  typeClass: string;


  // --------- Part of Mounting ----------------
  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margins: ModelMargin;
  //--------------------------------------------

  @serialize
  @deserialize
  uuid: string;

  @serialize
  @deserialize
  id: string;

  @serialize
  @deserialize
  orientation: string;

  @serialize
  @deserialize
  name: string;

  @serialize
  @deserialize
  code: string;

  @serialize
  @deserialize
  step: number;

  @serialize
  @deserialize
  imageUrl: string;

  @serialize
  @deserialize
  productPage: string;

  @serializeAs(ModelPropertyValue)
  @deserializeAs(ModelPropertyValue)
  properties: Array<ModelPropertyValue>;

  @serializeAs(ModelTranslation)
  @deserializeAs(ModelTranslation)
  translations: ModelTranslation;

  // ------------------ Part of RealModel ---------------
  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  dimension: ModelDimension;

  @serializeAs(ModelDimension)
  @deserializeAs(ModelDimension)
  maxDimension: ModelDimension;

  @serializeAs(ModelMargin)
  @deserializeAs(ModelMargin)
  margin: ModelMargin;
  //---------------------


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

  relations: Array<RelationDefinition> = [];

  static relatedRelations(m: Model, prevModels: Array<Model>): Array<RelationDefinition> {
    let relatedRelations: Array<RelationDefinition> = [];

    for (let prevModel of prevModels) {
      if (prevModel && prevModel.relations) {
        for (let relation of prevModel.relations) {
          for (let mrel of relation.models) {
            if (mrel.uuid === m.uuid) {
              relatedRelations.push(relation);
            }
          }
        }
      }
    }
    let foundRelations: Array<RelationDefinition> = [];
    let foundRelationsInfo: Array<RelationDefinition> = [];
    for (let rl of relatedRelations) {

      if (Model.isModelsTheSameAs(prevModels, rl.models)) {
        foundRelations.push(rl);

        if (rl.relationState == RelationState.ALLOWEDWITHINFO) {
          foundRelationsInfo.push(rl);
        }
      }
    }
    //console.info(foundRelationsInfo);
    return foundRelations;
  }

  private static isModelsTheSameAs(models: Array<Model>, prevModels: Array<Model>) {
    for (let m of models) {
      if (!Model.canBeFoundIn(m, prevModels)) {
        return false;
      }
    }
    return true;
  }

  private static canBeFoundIn(m: Model, prevModels: Array<Model>) {
    if (m!=undefined) {
      for (let pm of prevModels) {
        if (m.uuid === pm.uuid) {
          return true;

        }
      }
    }
    return false;
  }
}
