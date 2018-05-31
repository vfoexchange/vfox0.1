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

    post(url: string, data: any): Observable<any> {
    //debugger;
    let postUrl = this.configuration.ApiUrl + url;
    return this._http.post(postUrl, JSON.stringify(data), { headers: new HttpHeaders().set('Content-Type', 'application/json') })
      .map(this.extractData)
      .catch(this.handleError);
  }


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

    postWithToken(url: string, data: any): Observable<any> {
     debugger
    let postUrl = this.configuration.ApiUrl + url;


    this.headers = new Headers({

      'authorization': 'Bearer ' + localStorage.getItem('token')
    });
    this.options = new RequestOptions({ headers: this.headers });
    return this.http.post(postUrl, data, this.options)
      .map(res => res.json());

}






    /*
   post(url: string, data: any): Observable<any> {

    var headers = new Headers({ "authorization": "Basic MTIzNDU2OmRmdmJhZWZ2YWRlZnZhYw=="});
     headers.set('Content-Type', 'application/x-www-form-urlencoded');
    var options = new RequestOptions({ headers: headers });

    //debugger;
    let postUrl = this.configuration.ApiUrl + url;
    return this._http.post(postUrl, data, { headers: new HttpHeaders()
      .set("authorization", "Basic MTIzNDU2OmRmdmJhZWZ2YWRlZnZhYw==")
      .set('Content-Type', 'application/x-www-form-urlencoded') })
      .map(this.extractData)
      .catch(this.handleError);
  } */





    put(url: string, data: any): Observable<any> {
    //debugger;
    let postUrl = this.configuration.ApiUrl + url;
    return this._http.put(postUrl, JSON.stringify(data), { headers: new HttpHeaders().set('Content-Type', 'application/json') })
      .map(this.extractData)
      .catch(this.handleError);
  }

  createTextPlainHeader() {
    this.headers = new Headers();
    this.headers.set('Content-Type', 'application/json');
  }




   putLogin(url: string):Observable<any>{
    //for login
    let getLoginUrl = this.configuration.ApiUrl +this.configuration.API_LOGIN_URL;
    return this._http.post(getLoginUrl, {"username":"admin","password":"password@1","userType":"admin"}, { headers: new HttpHeaders().set('Content-Type', 'application/json') })
     .map(this.extractData)
     .catch(this.handleError);
   }

  postWithoutLogin(url: string, data: any): Observable<any> {
    ////debugger;
    let getUrl = this.configuration.ApiUrl + url;
    return this._http.post(getUrl, JSON.stringify(data), { headers: new HttpHeaders().set('Content-Type', 'application/json') })
      .map(this.extractData)
      .catch(this.handleError);
  }


putAfterLogin(url: string, data: any): Observable<any> {
      //debugger;
       let getUrl = this.configuration.ApiUrl + url;
    return this._http.put(getUrl, JSON.stringify(data), { headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('x-Auth-Token', localStorage.getItem('token')) })
      .map(this.extractData)
      .catch(this.handleError);
    //.filter(x => x.result.data.token != '') //Filter Or check the resonse data
    //.delay(2000) //Wait the response for 2 seconds

   }


    postAfterLogin(url: string, data: any): Observable<any> {
      //debugger;
       let getUrl = this.configuration.ApiUrl + url;
    return this._http.post(getUrl, JSON.stringify(data), { headers: new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('x-Auth-Token', localStorage.getItem('token')) })
      .map(this.extractData)
      .catch(this.handleError);
    //.filter(x => x.result.data.token != '') //Filter Or check the resonse data
    //.delay(2000) //Wait the response for 2 seconds

   }



 private extractData(response: Response) {
    return response;
  }

  private handleError = (error: any) => {
    //this._router.navigate(['/error']);

    // if(Object.keys(error).length > 0 && error.status == 406){
    //   this._toastr.error(error.error.message);
    // }

    return Observable.of([]);
 }

 postFile(url: string, data: any) {
  //this.configuration.ApiUrl + url
  //http://192.168.10.196:8787/mohips/api/customer/fileuplaod
  let postUrl = this.configuration.ApiUrl + url;
  this.headers = new Headers();
  // if (localStorage.getItem('token')) {
  //   this.headers.set('X-Auth-Token', localStorage.getItem('token'));
  // }
  this.headers.set("Accept", "application/json; charset=utf-8");

  return this.http.post(postUrl, data, {
    headers: this.headers
  })
    .map(this.extractData)
    .catch((error: any) => Observable.throw(error))

}



}
