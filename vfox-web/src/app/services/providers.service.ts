import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {Configuration} from "../common-services/app-constant";
import {HttpService} from "../common-services/http-service";
import { UtilService } from "../common-services/util-services";

@Injectable()
export class ProvidersService {
  serviceStatus: any;
  ServiceName: any;
  currentUser:any ;
constructor(private utilService: UtilService, private httpService: HttpService, private configuration: Configuration) {
   }


    serviceProviders(serviceType:string) {
    var url = this.configuration.API_SERVICE_PROVIDER+'?servicesName='+serviceType;
    return this.httpService.get(url);
   }


    getUserService(user:string) {
    return this.httpService.postWithToken(this.configuration.API_USER_SERVICE, {
         username: user
       });
   }

   updateService(value: any, userEmail: any) {
     //debugger
    return this.httpService.postWithToken(this.configuration.API_UPDATE_SERVICE, {
      username:userEmail,
      services:{
      'Insurance': value.insurance,
      'Alternate Investment': value.investment,
      'Bill Pay': value.bill_pay,
      'Wealth Management': value.welth_manage,
      'Tax Mitigation': value.tax_mitigation,
      'Asset Protection': value.asset_protection,
      'Trust Services': value.trust_services,
      'Business Valuation': value.business_valuation,
      'Cost Remediation': value.cost_remediation,
      'Business Transition': value.business_transition
      }
       });
   } 

   //Set footer menu for advisor and client
   bindUserMenu(response, serviceStatus){ 
debugger
    this.ServiceName = this.configuration.ServiceName;
    serviceStatus.bill_pay_menu = false;
    serviceStatus.investment_menu = false;
    serviceStatus.insurance_menu = false;
    serviceStatus.welth_manage_menu = false;
    serviceStatus.tax_mitigation_menu = false;
    serviceStatus.asset_protection_menu = false;
    serviceStatus.trust_services_menu = false;
    serviceStatus.business_valuation_menu = false;
    serviceStatus.cost_remediation_menu = false;
    serviceStatus.business_transition_menu = false;

    for (let child of response.result) {

      switch (child.name) {
        case this.ServiceName.bill_pay:
          serviceStatus.bill_pay_menu = true;
          break;
        case this.ServiceName.investment:
          serviceStatus.investment_menu = true;
          break;
          case this.ServiceName.insurance:
          serviceStatus.insurance_menu = true;
          break;
        case this.ServiceName.welth_manage:
          serviceStatus.welth_manage_menu = true;
          break;
        case this.ServiceName.tax_mitigation:
          serviceStatus.tax_mitigation_menu = true;
          break;
        case this.ServiceName.asset_protection:
          serviceStatus.asset_protection_menu = true;
          break;
        case this.ServiceName.trust_services:
          serviceStatus.trust_services_menu = true;
          break;
        case this.ServiceName.business_valuation:
          serviceStatus.business_valuation_menu = true;
          break;
        case this.ServiceName.cost_remediation:
          serviceStatus.cost_remediation_menu = true;
          break;
        case this.ServiceName.business_transition:
          serviceStatus.business_transition_menu = true;
          break;
        default:
        //Nothing
      }


    }
    //serviceStatus.bill_pay_menu = true;
    return serviceStatus;

   }



//Set Get Update services page buttons
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