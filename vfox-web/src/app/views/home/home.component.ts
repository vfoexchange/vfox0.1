import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { HomeService } from '../../services/home.service';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../common-services/util-services";
import { ValidationService } from '../../common-services/validation-services';
import { ToastrService } from 'ngx-toastr';

@Component({
  templateUrl: 'home.component.html'
})
export class HomeComponent {

  registerForm: FormGroup;
  //res: any;
  error: boolean = false;
  errorMsg: string = '';
  captchaImage: string;
  captchaValue: string;

  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, private homeService: HomeService,
    private utilService: UtilService, private http: Http, private _toastrService: ToastrService
  ) {
    translate.setDefaultLang('en');

  }

  ngOnInit() {
    //set advisor registration form field and validation
    var formBuilder = new FormBuilder();
    this.registerForm = formBuilder.group({
      username: new FormControl(null, [Validators.required, ValidationService.emailValidator]),
      password: new FormControl(null, [Validators.required, ValidationService.passwordValidator, Validators.minLength(8)]),
      confirmPassword: new FormControl(null, [Validators.required]),
      captcha: new FormControl(null, [Validators.required]),
    }, { validator: ValidationService.confirmPasswords('password', 'confirmPassword') });

    this.getCaptcha();

  }

  //After submit advisor registration form post data to API server
  onSubmit() {
    let obj = this.registerForm.value;
    if (obj.captcha == this.captchaValue) {
      if (obj.username !== '' || obj.password !== '') {
        this.homeService.register(obj.username, obj.password).subscribe(
          (response) => {
            if (this.utilService.isEmpty(response)) {
              this._toastrService.error("Something went wrong please try again", 'Oops!');
            }
            if (response.code == 200) {
              this._toastrService.success(response.msg);
              this.registerForm.reset();
            } else {
              this._toastrService.error(response.msg, 'Oops!');
              this.registerForm.reset();

            }
            this.captchaImage = '';
            this.captchaValue = '';
          },

          (error) => {
            this._toastrService.error("Something went wrong please try again", 'Oops!');
            this.utilService.logError(error);
          },
          () => { }

        );
      } else {
        this._toastrService.error("Email OR Password cannot be empty !", 'Oops!');

      }
    } else {
      this._toastrService.error("Invalid captcha !", 'Oops!');
    }

  }

  reloadCaptcha() {
    this.getCaptcha();
  }

  getCaptcha() {

    this.homeService.getCaptcha().subscribe(
      (response) => {
        this.captchaImage = response.captcha;
        this.captchaValue = response.captchCode;
        if (response.code == 200) {
          this.captchaImage = response.captcha;
        } else {
          // this.captchaImage = '';
        }
      },

      (error) => {
        this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      },
      () => { }

    );

  }



}//class ends//

//Email verification component
@Component({
  templateUrl: 'verifyemail.component.html'
})

export class VerifyEmailPageComponent {
  sub: any;
  verifyKey: string = '';
  verify: boolean = false;
  verifyMsg: string = '';

  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, private homeService: HomeService,
    private utilService: UtilService, private http: Http, private _toastrService: ToastrService) {
    //translate.setDefaultLang('en');
    /* 
                 this.sub = this.route.params.subscribe(
               (param: any) => {
                  // this.verifyKey = param['token'];
   
               });
           this.route.queryParams.subscribe(params => {
           this.verifyKey = params['startdate'];
   
               });
   
           this.route.params.subscribe(params => {
               this.verifyKey = params['id'];   //<----- + sign converts string value to number
           });
   
   //last working
       //
   */
    //Get token id from URL
    this.verifyKey = this.route.snapshot.paramMap.get('id');
    this.verifyEmailCode();

  }

  //Call email verification API to validate user token
  verifyEmailCode() {

    this.homeService.verifyEmail(this.verifyKey).subscribe(
      (response) => {

        if (response.code == 200) {
          //  this.router.navigate(['home']);
          this.verify = true;
        } else {
          // this._toastrService.error(response.msg, 'Oops!');
          this.verify = false;
          this.verifyMsg = response.msg;
        }
      }, (error) => {

      },
      () => { }
    );
  }


}
