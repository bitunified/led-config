import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ValidationInputComponent } from './validation-input/validation-input.component';
import {MdInputModule} from '@angular/material';
import {MdMenuModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { SidebComponent } from './sideb/sideb.component';
import {SidebarModule} from "ng-sidebar";
import {MaterialModule} from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    ValidationInputComponent,
    SidebComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MdInputModule,
    MdMenuModule,
    MaterialModule,
    BrowserAnimationsModule,
    SidebarModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
