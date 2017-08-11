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

    //console.info(prevModels);
    let prevModelInclCurrent:Array<Model>=[];


    prevModels.push(m);
    for (let prevModel of prevModels) {
      prevModelInclCurrent.push(prevModel);
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


    let lowPrevModelStep=m.step;
    for (let rl of relatedRelations) {
      let lowRelStep=Model.determineLowestStep(rl.models);

      //if ( lowPrevModelStep==lowRelStep) {
      //   console.info("c:"+Model.countSameModels(prevModelInclCurrent, rl.models));
      //   console.info(rl.models);
      //   console.info(prevModelInclCurrent);
      //   console.info(relatedRelations);
        //console.info(m);
      let count=Model.countSameModels(prevModelInclCurrent, rl.models);
        if ((count>=2 && rl.relationState==RelationState.ALLOWED)||(rl.relationState!=RelationState.ALLOWED && count==rl.models.length)) {
          foundRelations.push(rl);

          if (rl.relationState == RelationState.ALLOWEDWITHINFO) {
            foundRelationsInfo.push(rl);
          }
        }
      //}
    }
    //console.info(foundRelations);
    return foundRelations;
  }

  private static determineLowestStep(prevModels: Array<Model>) {
    let current:number=0;
    for (let m of prevModels){
      if (m.step>current){
        current=m.step;
      }
    }
    return current;
  }

  private static countSameModels(models: Array<Model>, prevModels: Array<Model>) {
    let notFound:number=0;
    for (let m of models) {
      if (!Model.canBeFoundIn(m, prevModels)) {
        notFound++;
      }
    }
    return models.length-notFound;
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

