import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {environment} from "../../environments/environment";
import {Part} from "../domain/server/Part";
import {Model} from "../domain/Model";
import {Serialize,Deserialize} from 'cerialize';
import {ProductConfiguration} from "../domain/ProductConfiguration";
import { URLSearchParams } from "@angular/http"
@Injectable()
export class PartService {

  constructor(private http: Http) {
  }

  private partServerUrl = environment.contextroot + '/server/rest/engine/part';

  public getPart(model: Model,productConfig:ProductConfiguration,currentStep:number):Observable<Part> {
    let params: URLSearchParams = new URLSearchParams();
    params.append("currentStep", String(currentStep));
    return this.http.post(this.partServerUrl,Serialize(productConfig,ProductConfiguration),{search:params})

      .map(res => {

        let m = Deserialize(res.json(), Part);
        return m;
      })
      .do(data => console.log(data))
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }
}



