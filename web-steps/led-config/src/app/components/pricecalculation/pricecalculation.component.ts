import {Component, OnInit, Inject, Optional} from "@angular/core";
import {PriceCalculation} from "../../domain/server/PriceCalculation";
import {MdDialogRef, MD_DIALOG_DATA} from "@angular/material";

@Component({
  selector: 'pricecalculation',
  templateUrl: './pricecalculation.component.html',
  styleUrls: ['./pricecalculation.component.css']
})
export class PricecalculationComponent implements OnInit {

  priceCalculation: PriceCalculation;

  constructor(@Optional() @Inject(MD_DIALOG_DATA) public data: any, public dialogRef: MdDialogRef<PricecalculationComponent>) {
  }


  ngOnInit() {
    this.priceCalculation = this.data;
  }

}
