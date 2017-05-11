import {Component, forwardRef, OnInit, OnDestroy} from '@angular/core';
import {ControlValueAccessor, NG_VALUE_ACCESSOR} from "@angular/forms";
import {ProductcodeService} from "../services/productcode.service";
import {Subscription} from "rxjs";
import {ValidationServiceService} from "../services/validation-service.service";

export const VALIDATION_INPUT_CONTROL_VALUE_ACCESSOR: any = {
  provide: NG_VALUE_ACCESSOR,
  useExisting: forwardRef(() => ValidationInputComponent),
  multi: true
};
const noop = () => {
};
@Component({
  selector: 'validation-input',
  templateUrl: './validation-input.component.html',
  styleUrls: ['./validation-input.component.css'],
  providers: [VALIDATION_INPUT_CONTROL_VALUE_ACCESSOR,ValidationServiceService]
})
export class ValidationInputComponent implements  OnDestroy,ControlValueAccessor {

  private subscription: Subscription;
  constructor(private productcodeService: ProductcodeService,validationService:ValidationServiceService ){
    this.subscription = this.productcodeService.notifyObservable$.subscribe((data) => {
      console.info(data);
      validationService.validateCode(data).subscribe(serverResponse=>
        console.info(serverResponse),
        error=>console.info('error'))


    });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }


  //The internal data model
  private innerValue: any = '';

  //Placeholders for the callbacks which are later providesd
  //by the Control Value Accessor
  private onTouchedCallback: () => void = noop;
  private onChangeCallback: (_: any) => void = noop;

  //get accessor
  get value(): any {
    return this.innerValue;
  };

  //set accessor including call the onchange callback
  set value(v: any) {
    if (v !== this.innerValue) {
      this.innerValue = v;
      this.onChangeCallback(v);
    }
  }

  //Set touched on blur
  onBlur() {
    this.onTouchedCallback();
  }

  //From ControlValueAccessor interface
  writeValue(value: any) {
    if (value !== this.innerValue) {
      this.innerValue = value;
    }
  }

  //From ControlValueAccessor interface
  registerOnChange(fn: any) {
    this.onChangeCallback = fn;
  }

  //From ControlValueAccessor interface
  registerOnTouched(fn: any) {
    this.onTouchedCallback = fn;
  }

}
