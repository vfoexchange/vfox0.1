import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { UtilService } from '../../../common-services/util-services';
import { Response, Http, Headers } from '@angular/http';
import { ProvidersService } from '../../../services/providers.service';
import { ToastrService } from 'ngx-toastr';
import { Configuration } from "../../../common-services/app-constant";


@Component({
  selector: 'app-select-service',
  templateUrl: './select-service.component.html',
})
export class ServiceCenterComponent implements OnInit {
  serviceForm: FormGroup;
  serviceStatus: any;
  userServiceStatus: any;
  ServiceName: any;
  currentUser: any;

  constructor(private translate: TranslateService, private providersService: ProvidersService,
    private utilService: UtilService, private http: Http, private _toastrService: ToastrService, private configuration: Configuration) {

  }
  ngOnInit() {
    // this.serviceStatus = 'true';
    this.ServiceName = this.configuration.ServiceName;
    this.serviceStatus = this.configuration.ServiceStatus;
    this.currentUser = this.utilService.getData('loginDataDetail');
    

    this.serviceForm = new FormGroup({
      insurance: new FormControl(null),
      bill_pay: new FormControl(null),
      investment: new FormControl(null),
      welth_manage: new FormControl(null),
      tax_mitigation: new FormControl(null),
      asset_protection: new FormControl(null),
      trust_services: new FormControl(null),
      business_valuation: new FormControl(null),
      cost_remediation: new FormControl(null),
      business_transition: new FormControl(null),
      charitable_strategies: new FormControl(null),
      concierge_services: new FormControl(null)
    });

    this.getUserService();
  }

  getUserService() {


    this.providersService.getUserService(this.currentUser.userEmail).subscribe(
      (response) => {

        if (this.utilService.isEmpty(response)) {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
        }

        if (response.code == 200) {

          this.serviceStatus = this.providersService.bindUserServices(response);

        } else {
          this._toastrService.error(response.msg, 'Oops!');
        }
      },

      (error) => {
        this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      },
      () => {
        console.log('Done');
      });
  }

  onSubmit() {
    //debugger
    let obj = this.serviceForm.value;
    // console.log(obj);
    this.providersService.updateService(obj, this.currentUser.userEmail).subscribe(
      (response) => {
        if (this.utilService.isEmpty(response)) {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
        }

        if (response.code == 200) {
          this._toastrService.success(response.msg);
          this.providersService.getUserService(this.currentUser.userEmail).subscribe(
            (responseData) => {
              if (responseData.code == 200) {
                
                this.userServiceStatus = this.configuration.footerMenu;
                this.userServiceStatus = this.providersService.bindUserMenu(responseData, this.userServiceStatus);
              }
            });

        } else {
          this._toastrService.error(response.msg, 'Oops!');
        }
      },

      (error) => {
        this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      },
      () => {
        console.log('Service update Complete');
      });
  }

}
