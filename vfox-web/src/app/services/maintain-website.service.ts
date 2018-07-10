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
            colourScheme: obj.colorScheme,
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
            colourScheme: obj.colorScheme,
            advisorId: userId
       });
     }  
     getBooks1(): TreeviewItem[] {
        const Category1 = new TreeviewItem({
            text: 'Insurance', value: 1,  children: [
                { text: 'Cool Springs', value: 11 },
                { text: 'Lorem ipsum', value: 12 },
                { text: 'Lorem ipsum ', value: 13 }
            ]
        });
    
        const Category2 = new TreeviewItem({
            text: 'Bill Pay', value: 1,  children: [
                { text: 'Lorem ipsum provider', value: 12 },
                { text: 'Lorem ipsum ', value: 13 },
                { text: 'Lorem ipsum ', value: 14 },
                { text: 'Lorem ipsum ', value: 14 }
            ]
        });
    
        const Category3 = new TreeviewItem({
            text: 'Alternative Investment', value: 1,  children: [
                { text: 'Lorem ipsum ', value: 14 },
                { text: 'Lorem ipsum ', value: 14 }
            ]
        });
    
        const Category4 = new TreeviewItem({
            text: 'Tax Mitigation', value: 1,  children: [
                { text: 'Restricted Property', value: 11 },
                { text: 'Ornstein-Schuler', value: 12 },
                { text: 'Lorem ipsum ', value: 13 },
                { text: 'Lorem ipsum ', value: 14 }
            ]
        });
       
        return [Category1, Category2, Category3, Category4];
    }

    getBooks2(): TreeviewItem[] {
        const Category1 = new TreeviewItem({
            text: 'Wealth Management', value: 1,  children: [
                { text: 'Lorem ipsum dolor provider', value: 11 },
                { text: 'Lorem ipsum provider', value: 12 },
            ]
        });
    
        const Category2 = new TreeviewItem({
            text: 'Asset Protection', value: 1,  children: [
                { text: 'Lorem ipsum provider', value: 12 },
                { text: 'Lorem ipsum ', value: 13 },
                { text: 'Lorem ipsum ', value: 14 },
                { text: 'Lorem ipsum ', value: 14 }
            ]
        });
    
        const Category3 = new TreeviewItem({
            text: 'Trust Services', value: 1,  children: [
                { text: 'Lorem ipsum ', value: 14 },
                { text: 'Lorem ipsum ', value: 14 }
            ]
        });
    
        const Category4 = new TreeviewItem({
            text: 'Cost Remadiation', value: 1,  children: [
                { text: 'Lorem ipsum dolor provider', value: 11 },
                { text: 'Lorem ipsum provider', value: 12 },
            ]
        });
       
        return [Category1, Category2, Category3, Category4];
    }

}
 