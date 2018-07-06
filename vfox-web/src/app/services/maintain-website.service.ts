import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {Configuration} from "../common-services/app-constant";
import {HttpService} from "../common-services/http-service";
import { TreeviewItem } from 'ngx-treeview';

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
            colourScheme: obj.colourScheme,
            advisorId: userId
       });
     } 
    //View Personalize API
    viewPersonalize(username:any) {
        return this.httpService.postWithToken(this.configuration.API_VIEW_PERSONALIZE_WEBSITE_URL, {
            username: username
       });
     }
     
    //Update Personalize API
    updatePersonalize(obj:any, filestring: string, userId:string) {
        return this.httpService.postWithToken(this.configuration.API_ADD_PERSONALIZE_WEBSITE_URL, {
            header: obj.pageHeader,
            description: obj.description,
            websiteLink: obj.websiteLink,
            logo: filestring,
            colourScheme: obj.colourScheme,
            advisorId: userId
       });
     }  

  getBooks(): TreeviewItem[] {
    const childrenCategory = new TreeviewItem({
        text: 'Children', value: 1, collapsed: true, children: [
            { text: 'Baby 3-5', value: 11 },
            { text: 'Baby 6-8', value: 12 },
            { text: 'Baby 9-12', value: 13 }
        ]
    });
    const itCategory = new TreeviewItem({
        text: 'IT', value: 9, children: [
            {
                text: 'Programming', value: 91, children: [{
                    text: 'Frontend', value: 911, children: [
                        { text: 'Angular 1', value: 9111 },
                        { text: 'Angular 2', value: 9112 },
                        { text: 'ReactJS', value: 9113, disabled: true }
                    ]
                }, {
                    text: 'Backend', value: 912, children: [
                        { text: 'C#', value: 9121 },
                        { text: 'Java', value: 9122 },
                        { text: 'Python', value: 9123, checked: false, disabled: true }
                    ]
                }]
            },
            {
                text: 'Networking', value: 92, children: [
                    { text: 'Internet', value: 921 },
                    { text: 'Security', value: 922 }
                ]
            }
        ]
    });
    const teenCategory = new TreeviewItem({
        text: 'Teen', value: 2, collapsed: true, disabled: true, children: [
            { text: 'Adventure', value: 21 },
            { text: 'Science', value: 22 }
        ]
    });
    const othersCategory = new TreeviewItem({ text: 'Others', value: 3, checked: false, disabled: true });
    return [childrenCategory, itCategory, teenCategory, othersCategory];
}


}
 