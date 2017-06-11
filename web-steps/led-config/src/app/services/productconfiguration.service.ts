import {Injectable} from "@angular/core";

import {Http} from "@angular/http";
import {Subject} from "rxjs/Subject";
import {ProductConfiguration} from "../domain/ProductConfiguration";
import {StepModel} from "../domain/StepModel";
import {Model} from "../domain/Model";
import {Observable} from "rxjs";
import {Deserialize} from 'cerialize';
import {PriceCalculation} from "../domain/server/PriceCalculation";

@Injectable()
export class ProductconfigurationService {

  private productconfigSource = new Subject<ProductConfiguration>();

  productconfigSource$ = this.productconfigSource.asObservable();

  public productConfiguration: ProductConfiguration = new ProductConfiguration();


  private serverUrl = 'http://localhost:8080/server/rest/engine/submitconfig';

  constructor(private http: Http) {
  }

  productconfigAnnouncement(step: StepModel, model: Model, value: number) {

    this.productConfiguration.assignModel(step, model, value);


    this.productconfigSource.next(this.productConfiguration);
  }

  sendProductConfigToServer() {


    return this.http.post(this.serverUrl, this.productConfiguration)

      .map(res => {

        let m = Deserialize(res.json(), PriceCalculation);

        console.info(m);
        return m;

      })
      .do(data => console.log(data))
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));


  }
}
