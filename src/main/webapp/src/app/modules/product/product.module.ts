import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductComponent } from './product.component';
import {FlexLayoutModule} from "@angular/flex-layout";
import {ProductService} from "./product.service";
import {MatDividerModule} from "@angular/material/divider";
import {MatListModule} from "@angular/material/list";


@NgModule({
  declarations: [ProductComponent],
  imports: [
    CommonModule,
    ProductRoutingModule,

    FlexLayoutModule,
    MatDividerModule,
    MatListModule
  ],
  providers: [ProductService]
})
export class ProductModule { }
