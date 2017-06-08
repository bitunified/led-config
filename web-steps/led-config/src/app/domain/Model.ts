import {ModelDimension} from "./ModelDimension";
import {RelationDefinition} from "./relations/RelationDefinition";
export class Model {

  uuid:string;
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

  relations:Array<RelationDefinition>=[];

  getRelatedRelations(prevModels:Array<Model>):Array<RelationDefinition>{
    let relatedRelations:RelationDefinition[]=[];
    for (let prevModel of prevModels){
      for (let relation of prevModel.relations){
        for (let rel of relation.models){
          if (rel.uuid===this.uuid){
            relatedRelations.push(relation);
          }
        }
      }
    }

    return relatedRelations;
  }
}
