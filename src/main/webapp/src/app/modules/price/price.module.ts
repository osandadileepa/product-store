import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {PriceRoutingModule} from './price-routing.module';
import {PriceComponent} from './price.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProductService} from "../product/product.service";
import {ReactiveFormsModule} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";


@NgModule({
  declarations: [PriceComponent],
  imports: [
    CommonModule,
    PriceRoutingModule,

    FlexLayoutModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatInputModule
  ],
  providers: [ProductService]
})
export class PriceModule { }
