import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {Configuration} from "../common-services/app-constant";
import {HttpService} from "../common-services/http-service";

@Injectable()
export class MaintainWebsite {
constructor(private httpService: HttpService, private configuration: Configuration) {
   }
   //Add new client API
    addPersonalize(obj:any, filestring: string, userId:string) {
        console.log('------------->>>', obj);
        return this.httpService.postWithToken(this.configuration.API_ADD_PERSONALIZE_WEBSITE_URL, {
            header: obj.pageHeader,
            description: obj.description,
            domainName: obj.domainName,
            logo: filestring,
            advisorId: userId
       });
     } 
}
 