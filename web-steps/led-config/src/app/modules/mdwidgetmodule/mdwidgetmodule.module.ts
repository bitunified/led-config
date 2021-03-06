import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CdkTableModule} from '@angular/cdk'
import {MdTableModule,MdDialogModule,MdCheckboxModule,MdGridListModule,MdChipsModule,MdSnackBarModule,MdSliderModule,MdListModule,MdTabsModule,MdSidenavModule,MdToolbarModule,MdInputModule,MdMenuModule,MdSelectModule,MdIconModule,MdButtonModule,MdCardModule} from '@angular/material';

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
    MdSnackBarModule,
    MdGridListModule,
    MdChipsModule,
    MdCheckboxModule,
    MdDialogModule,
    MdTableModule,
    CdkTableModule

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
  MdSliderModule,MdSnackBarModule,
    MdGridListModule,
    MdChipsModule,
    MdCheckboxModule,
    MdDialogModule,
    MdTableModule,
    CdkTableModule
  ],
  declarations: []
})
export class MdwidgetmoduleModule { }
