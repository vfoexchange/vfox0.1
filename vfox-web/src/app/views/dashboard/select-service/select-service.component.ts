import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-select-service',
  templateUrl: './select-service.component.html',
})
export class ServiceCenterComponent implements OnInit {
  serviceForm: FormGroup;
  serviceData: string;
  constructor() { }
  ngOnInit() {
    this.serviceData = 'true';
    this.serviceForm = new FormGroup({
      selectService: new FormControl(null),
      insurance: new FormControl(null),
      users: new FormControl(null),
      billPay: new FormControl(null),
      investment: new FormControl(null),
      wealthManagement: new FormControl(null),
      manageProfile: new FormControl(null),
      taxMigration: new FormControl(null),
    });
  }

  onSubmit() {
    debugger
    let obj = this.serviceForm.value;
    console.log(obj);
  }

}
