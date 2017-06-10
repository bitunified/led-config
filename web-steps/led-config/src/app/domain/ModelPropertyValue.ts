
import { deserialize } from 'cerialize';
export class ModelPropertyValue {


  @deserialize
  name: string;

  @deserialize
  value: string;


}
