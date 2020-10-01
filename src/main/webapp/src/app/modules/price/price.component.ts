import {Component, OnInit} from '@angular/core';
import {Product} from "../models/product";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PRICE_LIST_ENDPOINT, ProductService} from "../product/product.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {PriceCalculate} from "../models/price-calculate";
import {take} from "rxjs/operators";
import {FormDataModel} from "../models/form-data-model";

@Component({
  selector: 'app-teacher',
  templateUrl: './price.component.html',
  styleUrls: ['./price.component.scss']
})
export class PriceComponent implements OnInit {

  public products: Product[];
  public priceFormGroup: FormGroup;
  public priceList: PriceCalculate[];

  constructor(private fb: FormBuilder, private productService: ProductService, private http: HttpClient) {
    this.priceFormGroup = this.fb.group({
      productId: [null, Validators.required],
      unit: [null, Validators.required]
    });
  }

  ngOnInit() {
    // get all available products
    this.productService.getAllProducts().subscribe(value => this.products = value);
  }

  /***
   * calculate all the price data for selected unit range
   *
   * @author Osanda Wedamulla
   */
  public calculatePriceForRange() {

    const obj: FormDataModel = this.priceFormGroup.value;

    const id = obj.productId;
    const unit = obj.unit;

    let params: HttpParams = new HttpParams();
    params = params.append('unit', unit.toString());
    params = params.append('productId', id.toString());

    this.http.get<PriceCalculate[]>(PRICE_LIST_ENDPOINT,{params}).pipe(take(1))
      .subscribe(value => this.priceList = value);

  }// calculatePriceForRange()

}
