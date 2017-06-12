import { Component, OnInit,Input } from '@angular/core';
import {PriceCalculation} from "../../domain/server/PriceCalculation";

@Component({
  selector: 'pricecalculation',
  templateUrl: './pricecalculation.component.html',
  styleUrls: ['./pricecalculation.component.css']
})
export class PricecalculationComponent implements OnInit {

  @Input()
  priceCalculation:PriceCalculation;

  constructor() { }

  ngOnInit() {
  }

}
