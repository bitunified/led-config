import { Injectable } from '@angular/core';
import {Http,Headers} from "@angular/http";
import {Observable} from "rxjs";

@Injectable()
export class ValidationServiceService {


  constructor (private http: Http) {}

  // private instance variable to hold base url
  private validationServerUrl = 'http://localhost:8080/server/rest/engine/data';

   private urlEncode(obj: Object): string {
  let urlSearchParams = new URLSearchParams();
  for (let key in obj) {
    urlSearchParams.append(key, obj[key]);
  }
  return urlSearchParams.toString();
}
  // Fetch all existing comments
  public validateCode(code:string) : Observable<any>{
    // ...using get request
    let body=this.urlEncode({productcode:code});

    let headers = new Headers();
    headers.append('Content-Type','application/x-www-form-urlencoded');
    return this.http.post(this.validationServerUrl,body,{headers:headers})
    // ...and calling .json() on the response to return data
      .map(res => <any>res.json())
      .do(data => console.log(data))
      //...errors if any
      .catch((error:any) => Observable.throw(error.json().error || 'Server error'));

  }
}
