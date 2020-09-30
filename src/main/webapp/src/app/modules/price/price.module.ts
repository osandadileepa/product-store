import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PriceRoutingModule } from './price-routing.module';
import { PriceComponent } from './price.component';
import {FlexLayoutModule} from "@angular/flex-layout";


@NgModule({
  declarations: [PriceComponent],
  imports: [
    CommonModule,
    PriceRoutingModule,

    FlexLayoutModule
  ]
})
export class PriceModule { }
