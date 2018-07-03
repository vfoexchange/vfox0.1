import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {Configuration} from "../common-services/app-constant";
import {HttpService} from "../common-services/http-service";

@Injectable()
export class MaintainWebsite {
constructor(private httpService: HttpService, private configuration: Configuration) {
   }
   //Add new Personalize API
    addPersonalize(obj:any, filestring: string, userId:string) {
        return this.httpService.postWithToken(this.configuration.API_ADD_PERSONALIZE_WEBSITE_URL, {
            header: obj.pageHeader,
            description: obj.description,
            domainName: obj.domainName,
            websiteLink: obj.websiteLink,
            logo: filestring,
            advisorId: userId
       });
     } 
}
 