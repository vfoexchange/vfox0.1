import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../common-services/util-services";
import { ToastrService } from 'ngx-toastr';
//Home  page
@Component({
  templateUrl: 'home.component.html'
})
export class HomepageComponent {

  constructor() {
  }

}
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

  constructor() {
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


