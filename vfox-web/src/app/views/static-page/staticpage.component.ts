import { Component, TemplateRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HomeService } from '../../services/home.service';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../common-services/util-services";
import { ValidationService } from '../../common-services/validation-services';
import { ToastrService } from 'ngx-toastr';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
//Home  page ...Not in use
/*@Component({
  templateUrl: 'home.component.html'
})
export class HomepageComponent {

  constructor() {
  }

} */
//About us page
@Component({
  templateUrl: 'about.component.html'
})
export class AboutComponent {

  constructor() {
  }

}
//Contact us page
@Component({
  templateUrl: 'contact.component.html'
})
export class ContactComponent {
  contactForm: FormGroup;
  modalRef: BsModalRef;
  resultMsg: any;

  constructor(private router: Router, private homeService: HomeService,private modalService: BsModalService,
    private utilService: UtilService, private http: Http, private _toastrService: ToastrService) {
  }

  ngOnInit() {
    //set contact form field and validation
    var formBuilder = new FormBuilder();
    this.contactForm = formBuilder.group({
      firstName: new FormControl(null),
      lastName: new FormControl(null),
      mobile: new FormControl(null, [Validators.minLength(10), ValidationService.mobileValidator]),
      email:  new FormControl(null, [Validators.required, ValidationService.emailValidator]),
      comment: new FormControl(null)
    });
  }
  //Model popup
  openModal(contact_result: TemplateRef<any>) {
    this.modalRef = this.modalService.show(contact_result);
  } 


    //After submit contact form post data to API server
    onSubmit(contact_result) {
      this.contactForm.reset();          
      let obj = this.contactForm.value;
      this.homeService.postContact(obj).subscribe(
        (response) => {
          //debugger
          if (response.code == 200) {
            //Model popup show
            this.openModal(contact_result);
            this.resultMsg =response.msg;
            this.contactForm.reset();
          } else {
            this._toastrService.error(response.msg, 'Oops!');
            this.contactForm.reset();
          }
        },
  
        (error) => {
          this._toastrService.error("Something went wrong please try again", 'Oops!');
          this.utilService.logError(error);
        },
        () => { }
  
      );
  
    }


}
//What we can page
@Component({
  templateUrl: 'whatwedo.component.html'
})
export class WhatweComponent {

  constructor() {
  }

}

//Services static page
//Tax Mitigation page
@Component({
  templateUrl: 'service-pages/tax-mitigation.component.html'
})
export class ServiceTaxMitiComponent {

  constructor() {
  }

}

//Insurance page
@Component({
  templateUrl: 'service-pages/insurance.component.html'
})
export class ServiceInsuranceComponent {

  constructor() {
  }

}

//Alternate Investment page
@Component({
  templateUrl: 'service-pages/investment.component.html'
})
export class ServiceInvestmentComponent {

  constructor() {
  }

}

//Qualified Leverage Strategy page
@Component({
  templateUrl: 'service-pages/qualified-leverage.component.html'
})
export class QualifiedLeverageComponent {

  constructor() {
  }

}


