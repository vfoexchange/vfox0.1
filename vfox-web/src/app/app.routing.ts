import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { AuthGuard } from './common-services/auth-guard-service';
import { LoginComponent } from './views/login/login.component';
import { HomeComponent, VerifyEmailPageComponent } from './views/home/home.component';
import { AboutComponent, ContactComponent, WhatweComponent, HomepageComponent } from './views/static-page/staticpage.component';




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
    path: 'verify/:id',
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
  {
    path: 'aboutus',
    component: AboutComponent,
    data: {
      title: 'Aboutus Page'
    }
  },
  {
    path: 'contact',
    component: ContactComponent,
    data: {
      title: 'contactus Page'
    }
  },
  {
    path: 'whatwecan',
    component: WhatweComponent,
    data: {
      title: 'What We Can Page'
    }
  },
  {
    path: 'homepage',
    component: HomepageComponent,
    data: {
      title: 'Home Page'
    }
  },


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
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
