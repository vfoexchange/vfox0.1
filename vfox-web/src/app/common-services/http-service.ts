import { Injectable } from '@angular/core';
import 'rxjs/Rx';
import { Configuration } from "../common-services/app-constant";
import { Observable } from "rxjs/Observable";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Http, Headers, Response, RequestOptions } from '@angular/http';

@Injectable()
export class HttpService {
  apiUrl: string;
  headers: any;
  requestUrl: string;
  responseData: any;
  options: any;
  baseUrl: any;

  Login_Response: any;


  constructor(private _http: HttpClient, private configuration: Configuration, private http: Http) {
    let myLocation = window.location.href;
    this.headers = new Headers();

  }


  CONTEXT_PATH: string = '';
  // post method without user token
  post(url: string, data: any): Observable<any> {
    //debugger;
    let postUrl = this.configuration.ApiUrl + url;
    return this._http.post(postUrl, JSON.stringify(data), { headers: new HttpHeaders().set('Content-Type', 'application/json') })
      .map(this.extractData)
      .catch(this.handleError);
  }

  // post method for user login without user token
  postLogin(url: string, data: any): Observable<any> {
    this.headers = new Headers({
      "authorization": this.configuration.Authorization
    });

    this.headers.set('Content-Type', 'application/x-www-form-urlencoded');//application/x-www-form-urlencoded //application/json
    //this.headers.append('Access-Control-Allow-Credentials', 'true');
    this.options = new RequestOptions({ headers: this.headers });

    let postUrl = this.configuration.ApiUrl + url;
    return this.http.post(postUrl, data, this.options)
      .map(this.extractData)
      .catch(this.handleError);

  }

  // Get method with user token
  get(url: string): Observable<any> {
    //debugger
    let getUrl = this.configuration.ApiUrl + url;


    this.headers = new Headers({

      'authorization': 'Bearer ' + localStorage.getItem('token')
    });
    this.options = new RequestOptions({ headers: this.headers });
    return this.http.get(getUrl, this.options)
      .map(res => res.json());

  }
  // post method with user token
  postWithToken(url: string, data: any): Observable<any> {

    let postUrl = this.configuration.ApiUrl + url;


    this.headers = new Headers({

      'authorization': 'Bearer ' + localStorage.getItem('token')
    });
    this.options = new RequestOptions({ headers: this.headers });
    return this.http.post(postUrl, data, this.options)
      .map(res => res.json());

  }

  private extractData(response: Response) {
    return response;
  }

  private handleError = (error: any) => {
    return Observable.of([]);
  }

}
