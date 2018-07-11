import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../common-services/auth-guard-service';
import { DashboardComponent, ComingsoonPageComponent } from './dashboard.component';
import { ServiceCenterComponent } from './select-service/select-service.component';
import { AddClientComponent } from './maintain-website/manage-client/client.component';
import { ServiceProviderComponent } from './service-providers/serviceprovider.component';
import { AddPersonalizeComponent, ViewPersonalizeComponent, UpdatePersonalizeComponent } from './maintain-website/personalize/personalize.component';
import { SelectProviderComponent } from './maintain-website/select-provider/select-provider.component';
import { ChangePasswordComponent } from './manage-profile/manage-profile.component';

//Dashboard route
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
    path: 'maintainwebsite/addclient',
    component: AddClientComponent,
    data: {
    title: 'addclient'
   }
},

{
  path: 'comingsoon',
  component: ComingsoonPageComponent,
  data: {
  title: 'comingsoonpage'
 }
},
{
  path: 'maintainwebsite/addpersonalize',
  component: AddPersonalizeComponent,
  data: {
  title: 'addpersonalizewebsite'
 }
},
{
  path: 'maintainwebsite/viewpersonalize',
  component: ViewPersonalizeComponent,
  data: {
  title: 'viewpersonalizewebsite'
 }
},
{
  path: 'maintainwebsite/selectprovider',
  component: SelectProviderComponent,
  data: {
  title: 'Select Provider'
 }
},
{
  path: 'maintainwebsite/editpersonalize',
  component: UpdatePersonalizeComponent,
  data: {
  title: 'editpersonalizewebsite'
 }
},
{
  path: 'profile/changepassword',
  component: ChangePasswordComponent,
  data: {
  title: 'Change Password'
 }
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule {}
