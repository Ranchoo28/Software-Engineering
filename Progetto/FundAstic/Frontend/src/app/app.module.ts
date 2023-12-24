import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Component/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HowWorksComponent } from './Component/how-works/how-works.component';
import { LoginComponent } from './Component/login/login.component';
import { MenuLoggedComponent } from './Component/menu-logged/menu-logged.component';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule, MatSelectionList } from '@angular/material/list';
import { NotFoundComponent } from './Component/not-found-component/not-found.component';
import { MenuComponent } from './Component/menu/menu.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RegisterComponent } from './Component/register/register.component'
import { HttpClientModule } from '@angular/common/http'
import { RequestService } from './Services/RequestService';
import { ProxyService } from './Services/ProxyService';
import { CookieService } from 'ngx-cookie-service';
import { PublishFormComponent } from './Component/publish-form/publish-form.component';
import { FinanceFormComponent } from './Component/finance-form/finance-form.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDialogComponent } from './Component/alert-publisher/alert-publisher.component';
import {MatStepperModule} from '@angular/material/stepper';
import { MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatNativeDateModule, MatOptionModule} from '@angular/material/core'
import { AutoResizeDirective } from './Direttive/auto-resize.directive';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import { ShowcaseComponent } from './Component/showcase/showcase.component';



@NgModule({
  declarations: [
    AutoResizeDirective,
    AppComponent,
    HomeComponent,
    HowWorksComponent,
    LoginComponent,
    MenuLoggedComponent,
    NotFoundComponent,
    MenuComponent,
    RegisterComponent,
    PublishFormComponent,
    FinanceFormComponent,
    MatDialogComponent,
    ShowcaseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatListModule,
    MatStepperModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatRadioModule,
    MatInputModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule
  ],
  providers: [RequestService, CookieService, ProxyService],
  bootstrap: [AppComponent]
})
export class AppModule { }
