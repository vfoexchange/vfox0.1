import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UtilService } from '../../../common-services/util-services';
import { Response, Http, Headers } from '@angular/http';
import { ProvidersService } from '../../../services/providers.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-service-center',
  templateUrl: './insurance.component.html'
})
export class InsuranceComponent {

  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, private providersService: ProvidersService,
  private utilService : UtilService, private http: Http,private _toastrService: ToastrService) {

      // translate.setDefaultLang('en');
     }

  ngOnInit() {
   
       this.providersService.serviceProviders('Insurance').subscribe(
         (response) => {

           if(this.utilService.isEmpty(response)){
             this._toastrService.error("Something went wrong please try again", 'Oops!');
           }
           if (response.statusCode === 200) {
            this._toastrService.success(response.message);
             console.log('Register Response: ', response);
             //this.router.navigate(['home']);
           } else {
             this._toastrService.error(response.message, 'Oops!');


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
