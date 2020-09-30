import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {take} from "rxjs/operators";
import {Product} from "../models/product";
import {PriceCalculate} from "../models/price-calculate";

export const PRODUCT_ENDPOINT = 'products';
export const PRICE_ENDPOINT = 'price';
export const PRICE_LIST_ENDPOINT = 'price/list';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) { }

  /***
   * get product price from details
   *
   * @author Osanda Wedamulla
   *
   * @param productId
   * @param unit
   *
   */
  public getPriceFromProductIdAndUnit(productId: number, unit: number) : Observable<any> {

    let params: HttpParams = new HttpParams();
    params = params.append('unit', unit.toString());

    const url =  PRICE_ENDPOINT + '/' + productId;

    return this.http.get<PriceCalculate>(url, { params }).pipe(take(1));

  }// getPriceFromProductIdAndUnit()


  /***
   * get student details
   *
   * @author Osanda Wedammulla
   *
   * @param studentId
   */
  public getAllProducts() : Observable<Product[]> {
    return this.http.get<Product[]>(PRODUCT_ENDPOINT).pipe(take(1));
  }// getStudentDetailsFromId()


}// ProductService {}
