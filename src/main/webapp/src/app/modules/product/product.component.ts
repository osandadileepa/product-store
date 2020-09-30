import {Component, OnInit} from '@angular/core';
import {ProductService} from "./product.service";
import {Product} from "../models/product";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PriceCalculate} from "../models/price-calculate";

@Component({
  selector: 'app-student',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  public products: Product[] = [];
  public productFormGroup: FormGroup;
  public priceModel: PriceCalculate;

  constructor(private service: ProductService, private fb: FormBuilder) {
    this.productFormGroup = this.fb.group({
      productId: [null, Validators.required],
      unit: [0, Validators.required]
    });
  }

  ngOnInit() {
    this.getAllProductsAvailable();
  }

  /***
   *
   * get all product details
   *
   * @author Osanda Wedamulla
   */
  private getAllProductsAvailable() {

    this.service.getAllProducts().subscribe(value => {
      this.products = value;
    });

  }//getAllProductsAvailable()

  public calculatePrice() {

    console.log(this.productFormGroup.value);
    const obj: any = this.productFormGroup.value;
    const id = obj.productId;
    const unit = obj.unit;


    this.service.getPriceFromProductIdAndUnit(id, unit).subscribe(value => {
      this.priceModel = value;
    });
  }// calculatePrice()

}// ProductComponent {}
