import {deserialize} from "cerialize";
export class Part {

  @deserialize
  price: number;

  @deserialize
  description: string;

  @deserialize
  id: string;

  @deserialize
  type: string;
}
