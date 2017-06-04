import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MdSnackBarModule,MdSliderModule,MdListModule,MdTabsModule,MdSidenavModule,MdToolbarModule,MdInputModule,MdMenuModule,MdSelectModule,MdIconModule,MdButtonModule,MdCardModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MdToolbarModule,
    MdInputModule,
    MdMenuModule,
    MdSidenavModule,
    MdSelectModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
    MdTabsModule,
    MdListModule,
    MdSliderModule,
    MdSnackBarModule
  ],
  exports:[MdToolbarModule,
    MdInputModule,
    MdMenuModule,
    MdSidenavModule,
    MdSelectModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
  MdTabsModule, MdListModule,
  MdSliderModule,MdSnackBarModule],
  declarations: []
})
export class MdwidgetmoduleModule { }
