import { Component, OnInit,Input } from '@angular/core';
import {Observable} from 'rxjs';

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css']
})
export class MenustepsComponent implements OnInit {

  @Input()
  models:Array<DisplayModel>;

  @Input()
  steps:Observable<StepsModel>;

  currentStep:number;

  constructor() {

  }

  ngOnInit() {
    this.currentStep=1;



  }


}
