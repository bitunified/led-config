import { Injectable } from '@angular/core';
import {Subject} from 'rxjs/Subject';
@Injectable()
export class ProductcodeService {
  private notify = new Subject<string>();
  /**
   * Observable string streams
   */
  notifyObservable$ = this.notify.asObservable();


  public notifyOther(data: string) {
    if (data) {
      this.notify.next(data);
    }
  }
}
