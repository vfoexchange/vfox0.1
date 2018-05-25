import{NgModule}from'@angular/core';
import {Routes, RouterModule}from '@angular/router';

// Import Containers
import {DefaultLayoutComponent}from './containers';

import { P404Component}from './views/error/404.component';
import {P500Component} from './views/error/500.component';
import {LoginComponent}from './views/login/login.component';
//import { RegisterComponent } from './views/register/register.component';
import {HomeComponent, VerifyEmailPageComponent}from './views/home/home.component';
import {AuthGuard}from './common-services/auth-guard-service';



export const routes: Routes = [
{
path: '',
redirectTo: 'login',
pathMatch: 'full',
},
{
path: '404',
component: P404Component,
data: {
title: 'Page 404'
}
},
{
path: '500',
component: P500Component,
data: {
title: 'Page 500'
}
},
{
path: 'home',
component: HomeComponent,
data: {
title: 'Home Page'
}
},

{
path: 'verify',
component: VerifyEmailPageComponent,
data: {
title: 'Email Verification'
}
},
{
path: 'login',
component: LoginComponent,
data: {
title: 'Login Page'
}
},
/*{
path: 'register',
component: RegisterComponent,
data: {
title: 'Register Page'
}
}, */

{
path: 'dashboard',
component: DefaultLayoutComponent,
canActivateChild: [AuthGuard],
data: {
title: 'dashboard'
},
loadChildren: './views/dashboard/dashboard.module#DashboardModule'

}
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
