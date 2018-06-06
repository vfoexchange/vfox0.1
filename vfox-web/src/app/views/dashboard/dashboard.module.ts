import { NgModule } from '@angular/core';
import { ChartsModule } from 'ng2-charts/ng2-charts';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { ButtonsModule } from 'ngx-bootstrap/buttons';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ScModalModule } from 'angular-5-popup';
import { FileUploadModule } from "angular-file-uploader";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { ServiceCenterComponent } from './select-service/select-service.component';
import { InsuranceComponent } from './Insurance/insurance.component';
import { AddClientComponent } from './manage-client/client.component';
import { TaxComponent } from './tax-migitation/tax.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
    ChartsModule,
    BsDropdownModule,
    ButtonsModule.forRoot(),
    FormsModule,
    ReactiveFormsModule, ScModalModule, TranslateModule, FileUploadModule,
    NgbModule.forRoot()
  ],
  declarations: [ DashboardComponent,  ServiceCenterComponent, InsuranceComponent, AddClientComponent, TaxComponent]
})
export class DashboardModule {

  
 }
