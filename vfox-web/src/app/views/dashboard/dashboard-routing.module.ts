import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../common-services/auth-guard-service';
import { DashboardComponent } from './dashboard.component';
import { ServiceCenterComponent } from './select-service/select-service.component';
import { AddClientComponent } from './manage-client/client.component';
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
    path: 'addclient',
    component: AddClientComponent,
    data: {
    title: 'addclient'
   }
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
