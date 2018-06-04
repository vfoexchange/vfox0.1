import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../../common-services/util-services";
import { ValidationService } from '../../../common-services/validation-services';
import { ClientService } from '../../../services/client.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-ad-client',
  templateUrl: './add-client.component.html'
})
export class AddClientComponent {

  clientForm: FormGroup;
 
  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, public clientService: ClientService,
    private utilService : UtilService, private http: Http,private _toastrService: ToastrService
      ) { 

     }

  ngOnInit() {

    var formBuilder = new FormBuilder();
    this.clientForm = formBuilder.group({

    username: new FormControl(null, [Validators.required, ValidationService.emailValidator]),
    password: new FormControl(null, [Validators.required, ValidationService.passwordValidator, Validators.minLength(8)]),
    confirmPassword: new FormControl(null, [Validators.required]),
  }, { validator: ValidationService.confirmPasswords('password', 'confirmPassword') });
   


  } 


  onSubmit() {
    //debugger
           let obj = this.clientForm.value;
           if (obj.username !== '' || obj.password !== '') {
           this.clientService.addClient(obj.username, obj.password).subscribe(
             (response) => {
               if(this.utilService.isEmpty(response)){
                 this._toastrService.error("Something went wrong please try again", 'Oops!');
               }
               if (response.code == 200) {
                this._toastrService.success(response.msg);
                this.clientForm.reset();
               } else {
                 this._toastrService.error(response.msg, 'Oops!');
                 this.clientForm.reset();
    
             }
             },
    
             (error) => {
              this._toastrService.error("Something went wrong please try again", 'Oops!');
               this.utilService.logError(error);
             },
             () => {  }
    
           );
         } else {
            this._toastrService.error("Email OR Password cannot be empty !", 'Oops!');
    
       }
    
         }

}
