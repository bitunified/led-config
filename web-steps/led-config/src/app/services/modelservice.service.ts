import { Injectable } from '@angular/core';

import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Injectable()
export class ModelserviceService {

  constructor (private http: Http) {}

  // private instance variable to hold base url
  private modelsServerUrl = 'http://localhost:8080/server/rest/engine/models';

  public getModels() : Observable<any>{


    return this.http.get(this.modelsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => <any>res.json())
      .do(data => console.log(data))
      //...errors if any
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
}



