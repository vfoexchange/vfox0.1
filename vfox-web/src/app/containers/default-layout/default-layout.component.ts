import{Component, Input}from '@angular/core';
import {TranslateService}from '@ngx-translate/core';
import {TranslateLangService}from '../../services/translate.service';
import {UtilService}from "../../common-services/util-services";
import {ActivatedRoute, Router}from '@angular/router';
import {Configuration }from "../../common-services/app-constant";

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent {

   userType: string = '';
  constructor(private utilService: UtilService,private router:Router,
	private configuration:Configuration, private translate: TranslateService, private translateService: TranslateLangService) {

    

  }
  isCreatingAccount: boolean = true;
   ngOnInit(){
    setTimeout(() => { this.isCreatingAccount = false; }, 2000);
    this.translate.setDefaultLang('en');
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
