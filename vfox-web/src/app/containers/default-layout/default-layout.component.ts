import { Component, Input } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { TranslateLangService } from '../../services/translate.service';
import { UtilService } from "../../common-services/util-services";
import { ActivatedRoute, Router } from '@angular/router';
import { Configuration } from "../../common-services/app-constant";
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
   currentUser:any ;
  constructor(private utilService: UtilService,private router:Router, private providersService: ProvidersService, private _toastrService: ToastrService,
	private configuration:Configuration, private translate: TranslateService, private translateService: TranslateLangService) {

    

  }
  isCreatingAccount: boolean = true;
   ngOnInit(){
    //Set service name and status
    this.ServiceName = this.configuration.ServiceName;
    this.userServiceStatus = this.configuration.ServiceStatus;

    if (this.utilService.getData('loginDataDetail') !== null) {
      this.currentUser = this.utilService.getData('loginDataDetail');
    }

    setTimeout(() => { this.isCreatingAccount = false; }, 4000);
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
        
        this.userServiceStatus = this.providersService.bindUserServices(response);
            // console.log(this.providers);
          } 
        },
  
        (error) => {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
          this.utilService.logError(error);
        },
        () => { console.log('Registration Complete'); }
  
  
      );
}

  switchLanguage(language: string, userType: string) {
    this.translateService.translateLang(language, userType);
  } 
logout() {
    //this.token = undefined;
    localStorage.removeItem('token');
    localStorage.removeItem('loginDataDetail');

    localStorage.clear();
     this.router.navigate(['/']);


  }
}
