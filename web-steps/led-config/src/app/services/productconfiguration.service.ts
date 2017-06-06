import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {ProductConfiguration} from "../domain/ProductConfiguration";
import {StepModel} from "../domain/StepModel";
import {Model} from "../domain/Model";

@Injectable()
export class ProductconfigurationService {

  private productconfigSource = new Subject<ProductConfiguration>();

  productconfigSource$ = this.productconfigSource.asObservable();

  public productConfiguration: ProductConfiguration = new ProductConfiguration();


  productconfigAnnouncement(step: StepModel, model: Model) {

   this.productConfiguration.assignModel(step,model);


    this.productconfigSource.next(this.productConfiguration);
  }
}
