import {Injectable} from '@angular/core';
import 'rxjs/Rx';
import {Configuration} from "../common-services/app-constant";
import {HttpService} from "../common-services/http-service";

@Injectable()
export class ClientService {
constructor(private httpService: HttpService, private configuration: Configuration) {
   }

   addClient(username:string, password: String) {
       return this.httpService.post(this.configuration.API_REGISTER_URL, {
         username: username,
         password: password,
         role: this.configuration.CLIENT
       });
     }



  
}
 