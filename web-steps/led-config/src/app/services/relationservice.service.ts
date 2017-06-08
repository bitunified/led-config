import { Injectable } from '@angular/core';

import {Http} from "@angular/http";
import {Observable} from "rxjs/Rx";
import {Relations} from "../domain/relations/Relations";

@Injectable()
export class RelationService {

  constructor (private http: Http) {}

  private relationsServerUrl = 'http://localhost:8080/server/rest/engine/relations';

  public getRelations() : Observable<Relations>{


    return this.http.get(this.relationsServerUrl)
    // ...and calling .json() on the response to return data
      .map(res => <Relations>res.json())
      .do(data => console.log(data))
      //...errors if any
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
}



