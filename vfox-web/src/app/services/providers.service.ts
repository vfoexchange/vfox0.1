import{Injectable}from'@angular/core';
import 'rxjs/Rx';
import {Configuration}from "../common-services/app-constant";
import {HttpService}from "../common-services/http-service";
import { UtilService } from "../common-services/util-services";

@Injectable()
export class ProvidersService {
  serviceStatus: any;
  ServiceName: any;
  currentUser:any ;
constructor(private utilService: UtilService, private httpService: HttpService, private configuration: Configuration) {
   }


    serviceProviders(serviceType:string) {
    var url = this.configuration.API_SERVICE_PROVIDER+'?serviceName='+serviceType;
    return this.httpService.get(url);
   }


    getUserService(user:string) {
    return this.httpService.postWithToken(this.configuration.API_USER_SERVICE, {
         username: user
       });
   }

   updateService(value: any) {
     //debugger
    return this.httpService.postWithToken(this.configuration.API_UPDATE_SERVICE, {
      bill_pay: value.bill_pay,
      investment: value.investment,
      insurance: value.insurance,
      welth_manage: value.welth_manage,
      tax_mitigation: value.tax_mitigation,
      asset_protection: value.asset_protection,
      trust_services: value.trust_services,
      business_valuation: value.business_valuation,
      cost_remediation: value.cost_remediation,
      business_transition: value.business_transition
       });
   }

   bindUserServices(response){
//debugger
    this.ServiceName = this.configuration.ServiceName;
    this.serviceStatus = this.configuration.ServiceStatus;

    for (let child of response.result) {

      switch (child.name) {
        case this.ServiceName.bill_pay:
          this.serviceStatus.bill_pay = true;
          break;
        case this.ServiceName.investment:
          this.serviceStatus.investment = true;
          break;
        case this.ServiceName.insurance:
          this.serviceStatus.insurance = true;
          break;
        case this.ServiceName.welth_manage:
          this.serviceStatus.welth_manage = true;
          break;
        case this.ServiceName.tax_mitigation:
          this.serviceStatus.tax_mitigation = true;
          break;
        case this.ServiceName.asset_protection:
          this.serviceStatus.asset_protection = true;
          break;
        case this.ServiceName.trust_services:
          this.serviceStatus.trust_services = true;
          break;
        case this.ServiceName.business_valuation:
          this.serviceStatus.business_valuation = true;
          break;
        case this.ServiceName.cost_remediation:
          this.serviceStatus.cost_remediation = true;
          break;
        case this.ServiceName.business_transition:
          this.serviceStatus.business_transition = true;
          break;

        default:
        //Nothing
      }


    }
    //this.serviceStatus.insurance = true;
    return this.serviceStatus;

   }

}