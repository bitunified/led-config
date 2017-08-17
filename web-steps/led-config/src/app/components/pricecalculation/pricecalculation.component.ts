import {Component, OnInit, Inject, Optional} from "@angular/core";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
import {MdDialogRef, MD_DIALOG_DATA} from "@angular/material";
import {Part} from "../../domain/server/Part";
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'pricecalculation',
  templateUrl: './pricecalculation.component.html',
  styleUrls: ['./pricecalculation.component.css']
})
export class PricecalculationComponent implements OnInit {

  priceCalculation: PriceCalculation;

  constructor(@Optional() @Inject(MD_DIALOG_DATA) public data: any, public dialogRef: MdDialogRef<PricecalculationComponent>,public translate:TranslateService) {
  }


  ngOnInit() {
    this.priceCalculation = this.data;
  }

  getTranslatedDescription(part:Part){
    return part.getNameTranslated(this.translate.defaultLang);
  }
}
