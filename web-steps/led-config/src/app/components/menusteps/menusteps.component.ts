import { Component, OnInit,Input } from '@angular/core';
import {Observable} from 'rxjs';
import {MdSnackBar} from '@angular/material';
import {NotificationComponent} from "../notification/notification.component";
import {StepsModel} from "../../domain/StepsModel";

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css']
})
export class MenustepsComponent implements OnInit {


  @Input()
  steps:Observable<StepsModel>;

  stepsall:StepsModel;

  currentStep:number;

  constructor(public snackBar: MdSnackBar) {}

  openSnackBar() {
    this.snackBar.openFromComponent(NotificationComponent,null);
  }

  nextStep(){
    this.currentStep++;


  }
  prevStep(){
    if (this.currentStep>1){
      this.currentStep--;
    }

  }
  ngOnInit() {
    this.currentStep=1;

    this.steps.subscribe((res:StepsModel) => {
        this.stepsall=res;

        console.info(res);
      }
      ,
      error=>console.info('error'));


  }


}
