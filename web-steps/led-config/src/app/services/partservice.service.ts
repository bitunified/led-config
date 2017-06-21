import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {environment} from "../../environments/environment";
import {Part} from "../domain/server/Part";
import {Model} from "../domain/Model";
import {Serialize,Deserialize} from 'cerialize';

@Injectable()
export class PartService {

  constructor(private http: Http) {
  }

  private partServerUrl = environment.contextroot + '/server/rest/engine/part';

  public getPart(model: Model):Observable<Part> {
    return this.http.post(this.partServerUrl, Serialize(model, Model))

      .map(res => {

        let m = Deserialize(res.json(), Part);
        return m;
      })
      .do(data => console.log(data))
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));

  }
}



