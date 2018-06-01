import { Injectable } from '@angular/core';
import { Router, CanActivateChild, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Location } from '@angular/common';
import { Configuration } from './app-constant';
import 'rxjs/add/operator/filter';
import { UtilService } from './util-services';
import { Authentication } from './authentication';

@Injectable()
export class AuthGuard implements CanActivateChild {
  currentStat: any;
  baseUrl: string;
  constructor(private configuration: Configuration,private auth: Authentication, private router: Router, private location: Location, private utilService: UtilService
  ) {

    let getContextUrl = window.location.protocol + '//' + window.location.hostname +
      (window.location.port ? ':' + window.location.port : '');
    this.baseUrl = getContextUrl;
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    let url = '/dashboard/' + route.routeConfig.path.split('/:')[0];
    return this.checkLogin(url);
  }

  checkLogin(currentStat: any): boolean {

   let restrictedPageForAdmin: string[] = this.configuration.restrictedPageForAdmin;
    let restrictedPageForADVISOR: string[] = this.configuration.restrictedPageForADVISOR;
    let restrictedPageForUser: string[] = this.configuration.restrictedPageForUser;

    let resPage: boolean;

    let currentPath = currentStat.split(';')[0];
   
     if (this.utilService.getData('loginDataDetail') !== null) {
       if (this.utilService.getData('loginDataDetail').roleId.toString()) {
         switch (this.utilService.getData('loginDataDetail').roleId.toString()) {
           case this.configuration.ADMIN_ROLE_ID:
             resPage = restrictedPageForAdmin.indexOf(currentPath) === -1;
             break;
           
           
           case this.configuration.ADVISOR_ROLE_ID:
             
             resPage = restrictedPageForADVISOR.indexOf(currentPath) === -1;
             break;
           case this.configuration.CLIENT_ROLE_ID:
             
            resPage = restrictedPageForUser.indexOf(currentPath) === -1;
             break;
           
           default:
             //console.log('Selectd Role Not Defined');
         }
       }
     } else { window.location.href = this.baseUrl + this.configuration.HomeNavPageUrl; }

    if (this.utilService.getData('loginDataDetail').roleId === '' || this.utilService.getData('loginDataDetail').roleId === null || this.utilService.getData('loginDataDetail').roleId === undefined) {
      this.router.navigate(['login']);
    }

    if (resPage && this.utilService.getData('loginDataDetail').roleId) {

      this.auth.logout();

      window.location.href = this.baseUrl + this.configuration.HomeNavPageUrl;
      return false;
    } else {

      return true;
    }

  }
}
