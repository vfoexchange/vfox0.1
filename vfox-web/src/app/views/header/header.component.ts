import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HomeService } from '../../services/home.service';
import { Response, Http, Headers } from '@angular/http';
import { UtilService } from "../../common-services/util-services";
import { ToastrService } from 'ngx-toastr';

@Component({
    moduleId: module.id,
    selector: 'header-nav',
  templateUrl: 'header.component.html'
})
export class HeaderComponent {
  currentUser: any;

  constructor(private utilService: UtilService) {
    //Get current user
    this.currentUser = this.utilService.getData('loginDataDetail');

  }

}
