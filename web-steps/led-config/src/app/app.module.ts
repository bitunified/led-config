import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { MenustepsComponent } from './components/menusteps/menusteps.component';
import {MdwidgetmoduleModule} from './modules/mdwidgetmodule/mdwidgetmodule.module'
import { ReactiveFormsModule} from '@angular/forms';
import { MenustepitemComponent } from './components/menustepitem/menustepitem.component';
import { NotificationComponent } from './components/notification/notification.component';
@NgModule({
  declarations: [
    AppComponent,
    MenustepsComponent,
    MenustepitemComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    NoopAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MdwidgetmoduleModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
