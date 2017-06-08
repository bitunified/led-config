import {Model} from "../Model";
import {RelationState} from "./RelationState";
export class RelationDefinition {

  relationState:RelationState=RelationState.ALLOWED;
  allowedWithWarningMessage;

  models:Array<Model>;

}
