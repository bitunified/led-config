import { Injectable } from '@angular/core';

import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {StepsModel} from "../domain/StepsModel";

@Injectable()
export class StepsService {
  constructor (private http: Http) {}

  // private instance variable to hold base url
  private stepsServerUrl = 'http://localhost:8080/server/rest/engine/steps';

  public getSteps() : Observable<StepsModel>{


    return this.http.get(this.stepsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => <any>res.json())
      .do(data => console.log(data))
      //...errors if any
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }

}
