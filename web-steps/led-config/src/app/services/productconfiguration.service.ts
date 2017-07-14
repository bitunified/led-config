import {Injectable} from "@angular/core";

import {Http} from "@angular/http";
import {Subject} from "rxjs/Subject";
import {ProductConfiguration} from "../domain/ProductConfiguration";
import {StepModel} from "../domain/StepModel";
import {Model} from "../domain/Model";
import {Observable} from "rxjs";
import {Serialize, Deserialize} from 'cerialize';
import {PriceCalculation} from "../domain/server/PriceCalculation";
import { environment } from '../../environments/environment';
@Injectable()
export class ProductconfigurationService {

  private productconfigSource = new Subject<ProductConfiguration>();

  productconfigSource$ = this.productconfigSource.asObservable();

  private priceCalcSource = new Subject<PriceCalculation>();

  priceCalcSource$ = this.priceCalcSource.asObservable();

  public productConfiguration: ProductConfiguration = new ProductConfiguration();

  public priceCalculation:PriceCalculation;

  private serverUrl = environment.contextroot+'/server/rest/engine/submitconfig';

  constructor(private http: Http) {
  }

  productconfigAnnouncement(step: StepModel, model: Model, value: number) {

    this.productConfiguration.assignModel(step, model, value);


    this.productconfigSource.next(this.productConfiguration);
  }

  sendProductConfigToServer() {

    this.preProcessing();
    let serProductConfig = Serialize(this.productConfiguration, ProductConfiguration);
    return this.http.post(this.serverUrl, serProductConfig)

      .map(res => {

        let m = Deserialize(res.json(), PriceCalculation);

        this.priceCalculation=m;
        this.priceCalcSource.next(m);

        return m;

      })
      .do(data => console.log(data))
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));


  }

  private preProcessing() {
    this.productConfiguration.getModelChosenFromStep(3).chosenModel.dimension.width=this.productConfiguration.getModelChosenFromStep(7).modelValue;
    this.productConfiguration.getModelChosenFromStep(7).chosenModel=this.productConfiguration.getModelChosenFromStep(3).chosenModel;
    console.info(this.productConfiguration);
  }
}
