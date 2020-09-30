import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {take} from "rxjs/operators";

export const STUDENT_ENDPOINT = 'students';
export const RESULT_ENDPOINT = 'results';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) { }

  /***
   * get results for a student with pagination
   *
   * @author Osanda Wedamulla
   *
   * @param studentId
   * @param page
   * @param size
   *
   */
  public getResultsFromStudentId(studentId: number, page?: number, size?: number) : Observable<any> {

    let params: HttpParams = new HttpParams();
    params = params.append('projection', 'basic');

    if(page)
      params = params.append('page', page.toString());

    if(size)
      params = params.append('size', size.toString());

    const url =  STUDENT_ENDPOINT + '/' + studentId + '/' + RESULT_ENDPOINT;

    return this.http.get<any>(url, { params }).pipe(take(1));
  }// getResultsFromStudentId()


  /***
   * get student details
   *
   * @author Osanda Wedammulla
   *
   * @param studentId
   */
  public getStudentDetailsFromId(studentId: number) : Observable<any> {
    const url = STUDENT_ENDPOINT + '/' + studentId;
    return this.http.get<any>(url).pipe(take(1));
  }// getStudentDetailsFromId()


}// ProductService {}
