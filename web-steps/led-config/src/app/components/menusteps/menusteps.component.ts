import { Component, OnInit,Input } from '@angular/core';

@Component({
  selector: 'menusteps',
  templateUrl: './menusteps.component.html',
  styleUrls: ['./menusteps.component.css']
})
export class MenustepsComponent implements OnInit {

  @Input()
  titles:Array<string>;

  constructor() {


  }

  ngOnInit() {

  }


}
