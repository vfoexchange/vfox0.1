import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../common-services/auth-guard-service';
import { DashboardComponent } from './dashboard.component';
import { ServiceCenterComponent } from './select-service/select-service.component';
import { InsuranceComponent } from './Insurance/insurance.component';
import { AddClientComponent } from './manage-client/client.component';
import { TaxComponent } from './tax-migitation/tax.component';
import { CostComponent } from './cost-remediation/cost.component';
import { ServiceProviderComponent } from './service-providers/serviceprovider.component';


const routes: Routes = [
   {
    path: '',
    component: DashboardComponent,
    pathMatch:'full',
    data: {
      title: 'Dashboard'
    }
  },
  {
      path: 'selectservices',
      component: ServiceCenterComponent,
      data: {
      title: 'Service Center'
     }
  },
  {
    path: 'service/:name',
    component: ServiceProviderComponent,
    data: {
    title: 'Insurance'
   }
},
  {
      path: 'insurance',
      component: InsuranceComponent,
      data: {
      title: 'Insurance'
     }
  },
  {
    path: 'addclient',
    component: AddClientComponent,
    data: {
    title: 'addclient'
   }
},
{
  path: 'taxmitigation',
  component: TaxComponent,
  data: {
  title: 'Tax Mitigation'
 }
},
{
  path: 'costremediation',
  component: CostComponent,
  data: {
  title: 'Tax Remediation'
 }
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
