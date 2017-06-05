import { Injectable } from '@angular/core';
import { Subject }    from 'rxjs/Subject';
import {ProductCodeComposition} from "../domain/ProductCodeComposition";

@Injectable()
export class ProductcodeService {

  private productcodeSource = new Subject<ProductCodeComposition>();

  productcodeSource$ = this.productcodeSource.asObservable();

  productCodeComposition:ProductCodeComposition=new ProductCodeComposition();


  productcodeAnnouncement(code: string,step:number) {

    if (this.productCodeComposition.codes!==undefined) {
      this.productCodeComposition.codes[step] = code;
    }else{
      this.productCodeComposition.codes=[""];
    }
    this.productcodeSource.next(this.productCodeComposition);
  }
}
