import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Rx';
import { Configuration } from './app-constant';
import { ActivatedRoute, Router } from '@angular/router';

// Google's login API namespace
declare var gapi: any;

@Injectable()
export class Authentication {
  token: string;
  data: any;
  constructor( private configuration: Configuration,private router:Router) {
    this.token = localStorage.getItem('token');
  }


  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('loginDataDetail');
    localStorage.removeItem('isSplashShow');
    return Observable.of(true);

  }

  public signOut(): void {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut();
  }
}
