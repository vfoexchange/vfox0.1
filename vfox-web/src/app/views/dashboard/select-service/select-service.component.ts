import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { UtilService } from '../../../common-services/util-services';
import { Response, Http, Headers } from '@angular/http';
import { ProvidersService } from '../../../services/providers.service';
import { ToastrService } from 'ngx-toastr';
import {Configuration}from "../../../common-services/app-constant";


@Component({
  selector: 'app-select-service',
  templateUrl: './select-service.component.html',
})
export class ServiceCenterComponent implements OnInit {
  serviceForm: FormGroup;
  serviceStatus: string;
  ServiceName: string;

  constructor(private translate: TranslateService, private providersService: ProvidersService,
  private utilService : UtilService, private http: Http,private _toastrService: ToastrService, private configuration: Configuration) {

   }
  ngOnInit() {
   // this.serviceStatus = 'true';
    this.ServiceName = this.configuration.ServiceName;
    this.serviceStatus = this.configuration.ServiceStatus;

    this.serviceForm = new FormGroup({
      selectService: new FormControl(null),
      insurance: new FormControl(null),
      users: new FormControl(null),
      billPay: new FormControl(null),
      investment: new FormControl(null),
      wealthManagement: new FormControl(null),
      manageProfile: new FormControl(null),
      taxMigration: new FormControl(null),
    });

    this.getUserService();
  }

    getUserService(){

        this.ServiceName = this.configuration.ServiceName;
    this.serviceStatus = this.configuration.ServiceStatus;
         this.providersService.getUserService('advisor@advisor.com').subscribe(
         (response) => {

           if(this.utilService.isEmpty(response)){
             this._toastrService.error("Something went wrong please try again", 'Oops!');
           }

           if (response.code == 200) {
            this._toastrService.success(response.message);
          // response.result;
debugger;
 for(let child of response.result){
        if(child.name == this.ServiceName ){
       // this.serviceStatus.bill_pay = true;
    }
    if(child.name == this.ServiceName ){
       // this.serviceStatus.investment = true;
    }


   }


           // console.log(this.providers);
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

  onSubmit() {
    debugger
    let obj = this.serviceForm.value;
    console.log(obj);
  }

}
