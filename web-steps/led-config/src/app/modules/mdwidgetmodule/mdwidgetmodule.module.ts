import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MdTabsModule,MdSidenavModule,MdToolbarModule,MdInputModule,MdMenuModule,MdSelectModule,MdIconModule,MdButtonModule,MdCardModule} from '@angular/material';

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
    MdTabsModule
  ],
  exports:[MdToolbarModule,
    MdInputModule,
    MdMenuModule,
    MdSidenavModule,
    MdSelectModule,
    MdIconModule,
    MdButtonModule,
    MdCardModule,
  MdTabsModule],
  declarations: []
})
export class MdwidgetmoduleModule { }
