import{Injectable}from'@angular/core';
import 'rxjs/Rx';
import {Configuration}from "../common-services/app-constant";
import {HttpService}from "../common-services/http-service";

@Injectable()
export class ProvidersService {
constructor(private httpService: HttpService, private configuration: Configuration) {
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

}