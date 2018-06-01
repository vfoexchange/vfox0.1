import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { LoginService } from '../../services/login.service';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../common-services/util-services";
import { ValidationService } from '../../common-services/validation-services';
import { ToastrService } from 'ngx-toastr';

@Component({
  templateUrl: 'login.component.html'
})
export class LoginComponent {

  loginForm: FormGroup;
  //res: any;
  error: boolean = false;
  errorMsg: string = '';
  

  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, private loginService: LoginService,
  private utilService : UtilService, private http: Http,private _toastrService: ToastrService
    ) { 
       translate.setDefaultLang('en');
        if (this.utilService.getData('loginDataDetail') !== null) {
            if (this.utilService.getData('loginDataDetail').roleId.toString()) {
                this.router.navigate(['dashboard']);
            }
        }
     }

  ngOnInit() {
    this.loginForm = new FormGroup({
      username: new FormControl(null, [Validators.required, ValidationService.emailValidator]),
      password: new FormControl(null, [Validators.required, ValidationService.passwordValidator, Validators.minLength(8)]),
    });
  }


  onSubmit() {  
    let obj = this.loginForm.value;   
    if (obj.username !== '' || obj.password !== '') {
    this.loginService.login(obj.username, obj.password).subscribe(
      (response) => {
        if(this.utilService.isEmpty(response)){
          this._toastrService.error("Please Enter Correct Username or Password", 'Oops!');
        }
        response = response.json();
        
        if (response.access_token) {
           //set token and get profile
          localStorage.setItem('token', response.access_token);
          
          this.loginService.getUser(obj.username).subscribe(
            (response) => {
              
              if (response.code == 200) {
                //response.result;
                let loginDataTest = {
                  role: "Admin",
                  //role: response.result.role,
                  roleId: response.result.roleId,
                  userEmail:response.result.username,
                  userName:response.result.username,
                  firstLogin:response.result.firstLogin,
                  loggedIn: 'true'
              }
              this.utilService.setData(loginDataTest, 'loginDataDetail');
              if(response.result.firstLogin){
                this.router.navigate(['dashboard/selectservices']);
              }else{
                this.router.navigate(['dashboard']);
              }

              }

            });

        } else {
          this._toastrService.error( response.message, 'Oops!'); //"Your username OR password is invalid !";
          this.loginForm.reset();

      }    
      },

      (error) => {
        this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);

      },
      () => { }


    );
  } else {
    this._toastrService.error(" Email OR Password cannot be empty !", 'Oops!');
}

  }


}//class ends//
