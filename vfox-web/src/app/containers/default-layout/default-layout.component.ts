import { Component, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { TranslateLangService } from '../../services/translate.service';
import { UtilService } from "../../common-services/util-services";
import { ActivatedRoute, Router } from '@angular/router';
import { Configuration } from "../../common-services/app-constant";
import { Authentication } from "../../common-services/authentication";
import { ProvidersService } from '../../services/providers.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent {

  userType: string = '';
  userServiceStatus: any;
  ServiceName: any;
  currentUser: any;
  isCreatingAccount: boolean ;
  constructor(private utilService: UtilService, private router: Router, private auth: Authentication, private providersService: ProvidersService, private _toastrService: ToastrService,
    private configuration: Configuration, private translate: TranslateService, private translateService: TranslateLangService) {

      

  }
  
  ngOnInit() {
    //Set service name and status
    this.ServiceName = this.configuration.ServiceName;
    this.userServiceStatus = this.configuration.footerMenu;

    this.currentUser = this.utilService.getData('loginDataDetail');
    this.isCreatingAccount = this.utilService.getData('isSplashShow');

    setTimeout(() => { this.isCreatingAccount = false;
    //Set Splash false
    this.utilService.setData(false, 'isSplashShow');
    }, 4000);
    this.translate.setDefaultLang('en');
    this.getUserService();
  }

  getUserService() {

    this.providersService.getUserService(this.currentUser.userEmail).subscribe(
      (response) => {

        if (this.utilService.isEmpty(response)) {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
        }

        if (response.code == 200) {

          this.userServiceStatus = this.providersService.bindUserMenu(response, this.userServiceStatus);
          // console.log(this.providers);
        }
      },

      (error) => {
        this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      },
      () => { console.log('Done'); }


    );
  }

  //Logout current user
 /* logout() {
    //this.token = undefined;
    localStorage.removeItem('token');
    localStorage.removeItem('loginDataDetail');
    localStorage.removeItem('isSplashShow');

    localStorage.clear();
    this.router.navigate(['/']);

  } */
  logout() {
    this.auth.logout().subscribe(
      () => {
        this.router.navigate(['/']);
      }
    );
  }
}
