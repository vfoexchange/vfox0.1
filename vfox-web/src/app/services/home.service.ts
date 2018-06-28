import { Injectable } from '@angular/core';
import 'rxjs/Rx';
import { Configuration } from "../common-services/app-constant";
import { HttpService } from "../common-services/http-service";

@Injectable()
export class HomeService {
  constructor(private httpService: HttpService, private configuration: Configuration) {
  }
  //Add new advisor API
  register(username: string, password: String) {
    return this.httpService.post(this.configuration.API_REGISTER_URL, {
      username: username,
      password: password,
      role: this.configuration.ADVISOR
    });
  }
  //verify email API
  verifyEmail(emailKey: string) {
    return this.httpService.post(this.configuration.API_EMAIL_VERIFY, {
      username: emailKey
    });
  }

  //Get captcha API
  getCaptcha() {
    return this.httpService.post(this.configuration.API_GET_CAPTCHA, {
    });
  }
  postContact(contact: any) {
    return this.httpService.post(this.configuration.API_POST_CONTACT, {
      firstName: contact.firstName,
      lastName: contact.lastName,
      phone: contact.mobile,
      email: contact.email,
      comments: contact.comment
    });

  }

}
