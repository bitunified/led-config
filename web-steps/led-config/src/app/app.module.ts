import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {AppComponent} from "./app.component";
import {MenustepsComponent} from "./components/menusteps/menusteps.component";
import {MdwidgetmoduleModule} from "./modules/mdwidgetmodule/mdwidgetmodule.module";
import {MenustepitemComponent} from "./components/menustepitem/menustepitem.component";
import {NotificationComponent} from "./components/notification/notification.component";
import {FilterModel} from "./pipes/FilterModel";
import {PricecalculationComponent} from "./components/pricecalculation/pricecalculation.component";
import {TranslateModule} from "@ngx-translate/core";
import {ClipboardModule} from "ngx-clipboard";

@NgModule({
  declarations: [
    AppComponent,
    MenustepsComponent,
    MenustepitemComponent,
    FilterModel,
    PricecalculationComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    FormsModule,
    ReactiveFormsModule,
    MdwidgetmoduleModule,
    ClipboardModule,
    TranslateModule.forRoot()

  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents:[
    NotificationComponent,
  PricecalculationComponent]
})
export class AppModule { }
