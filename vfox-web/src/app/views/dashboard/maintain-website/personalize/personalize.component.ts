import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { TranslateService } from '@ngx-translate/core';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../../../common-services/util-services";
import { Configuration } from "../../../../common-services/app-constant";
import { ValidationService } from '../../../../common-services/validation-services';
import { MaintainWebsite } from '../../../../services/maintain-website.service';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-ad-personalize',
  templateUrl: './add-personalize.component.html'
})
export class AddPersonalizeComponent {

  PersonalizeForm: FormGroup;
  filestring:any;
  colorSchemeType:any;
  currentUser:any ;
  fileName:any = 'Choose File';
  filecheck: boolean = false;
  fileTypeCheck : boolean = false;
  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, public maintainWebsite: MaintainWebsite,
    private utilService : UtilService, private http: Http,private _toastrService: ToastrService, private configuration: Configuration
      ) { 
      this.colorSchemeType = configuration.ColorSchemeList;

     }

  ngOnInit() {
    var formBuilder = new FormBuilder();
    this.currentUser = this.utilService.getData('loginDataDetail');
    this.PersonalizeForm = formBuilder.group({
   
    pageHeader: new FormControl(null, [Validators.required]),
    description: new FormControl(null, [Validators.required]),
    uploadLogo: new FormControl(null, [Validators.required]),
    websiteLink: new FormControl(null),
    colorScheme: new FormControl(null),
    domainName: new FormControl(null, [Validators.required]),
  });
   
} 

onFileChanged(event) { 
  this.filecheck = false;
  this.fileTypeCheck = false;
  this.fileName = 'Choose File';
  let files = event.target.files[0];
  if (files){ 
    // check file size max 5kb and file type ["image/jpeg", "image/png", "image/gif", "image/jpg"] is valid , select by user
    if ( this.configuration.allowedImageType.indexOf(files.type) < 0  || files.size > 50200){
      this.fileTypeCheck = true
    }else{
      this.filecheck = true; // Checking file already selected by user,then no required message come to screen
      this.fileTypeCheck = false
      this.fileName = files.name;
      let reader = new FileReader(); 
      reader.readAsBinaryString(files); 
      reader.onload = this._handleReaderLoaded.bind(this); 
    }
    
  }

}
_handleReaderLoaded(readerEvt) { 
  let binaryString = readerEvt.target.result; 
  this.filestring = btoa(binaryString);  // Converting binary string data. 
} 


onSubmit() {
  let obj = this.PersonalizeForm.value;
  if (this.PersonalizeForm.dirty && this.PersonalizeForm.valid) {
    this.maintainWebsite.addPersonalize(obj, this.filestring, this.currentUser.userId).subscribe(
      (response) => {
        if(this.utilService.isEmpty(response)){
          this._toastrService.error("Something went wrong please try again", 'Oops!');
        }
        if (response.code == 200) {
         this._toastrService.success(response.msg);
         this.PersonalizeForm.reset();
         this.fileName = 'Choose File';
        } else {
          this._toastrService.error(response.msg, 'Oops!');
        }
      },
      (error) => {
       this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      });
  }
}

}


@Component({
  selector: 'app-view-personalize',
  templateUrl: './view-personalize.component.html'
})
export class ViewPersonalizeComponent {
   currentUser:any ;
   viewHeader:any ;
   viewDomainName:any ;
   viewDescription:any ;
   viewLogo:any ;
   viewWebsiteLink: any;
   viewColourScheme: any;
  constructor(private route: ActivatedRoute, private router: Router, private translate: TranslateService, public maintainWebsite: MaintainWebsite,
    private utilService : UtilService, private http: Http,private _toastrService: ToastrService, private configuration: Configuration
      ) { 
     }

  ngOnInit() {
    this.currentUser = this.utilService.getData('loginDataDetail');
    this.getPersonalizeList(this.currentUser);
}

getPersonalizeList(currentUser:any) {
  let username = currentUser.userEmail
  if (username) {
    this.maintainWebsite.viewPersonalize(username).subscribe(
      (response) => {
        if(this.utilService.isEmpty(response)){
          this._toastrService.error("Something went wrong please try again", 'Oops!');
        }
        if (response.code == 200) {
          let result = response.result 
          this.viewHeader = result.header ;
          this.viewDomainName = result.domainName ;
          this.viewLogo = result.logo ;
          this.viewDescription = result.description ;
          this.viewWebsiteLink = result.websiteLink ;
          this.viewColourScheme = result.colourScheme ;
        } else {
          this._toastrService.error(response.msg, 'Oops!');
        }
      },
      (error) => {
       this._toastrService.error("Something went wrong please try again", 'Oops!');
        this.utilService.logError(error);
      });
  }
}

}
