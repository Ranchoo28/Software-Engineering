import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Component/home/home.component';
import { HowWorksComponent } from './Component/how-works/how-works.component';
import { LoginComponent } from './Component/login/login.component';
import { NotFoundComponent } from './Component/not-found-component/not-found.component';
import { RegisterComponent } from './Component/register/register.component';
import { MenuLoggedComponent } from './Component/menu-logged/menu-logged.component';
import { PublishFormComponent } from './Component/publish-form/publish-form.component';
import { FinanceFormComponent } from './Component/finance-form/finance-form.component';


const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'how-works', component: HowWorksComponent},
  { path: 'login' ,component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'menu-logged', component: MenuLoggedComponent},
  { path: 'publish-project', component: PublishFormComponent},
  { path: 'finance-project', component: FinanceFormComponent},
  { path: '404', component: NotFoundComponent},
  { path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
