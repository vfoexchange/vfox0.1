import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UtilService } from '../../../common-services/util-services';
import { Response, Http, Headers } from '@angular/http';
import { ProvidersService } from '../../../services/providers.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-select-service',
  templateUrl: './insurance.component.html'
})
export class InsuranceComponent {
    providers: any;

  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, private providersService: ProvidersService,
  private utilService : UtilService, private http: Http,private _toastrService: ToastrService) {

      // translate.setDefaultLang('en');
     }

  ngOnInit() {
   
       this.providersService.serviceProviders('insurance').subscribe(
         (response) => {

           if(this.utilService.isEmpty(response)){
             this._toastrService.error("Something went wrong please try again", 'Oops!');
           }

           if (response.code == 200) {
           // this._toastrService.success(response.message);
            this.providers = response.result;
           } else {
             this._toastrService.error(response.msg, 'Oops!');


         }
         },

         (error) => {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
           this.utilService.logError(error);
         },
         () => { console.log('Registration Complete'); }


       );

  }

}
