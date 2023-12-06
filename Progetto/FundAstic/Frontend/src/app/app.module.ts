import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './Component/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HowWorksComponent } from './Component/how-works/how-works.component';
import { LoginComponent } from './Component/login/login.component';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { NotFoundComponent } from './Component/not-found-component/not-found.component';
import { MenuComponent } from './Component/menu/menu.component';
import {ReactiveFormsModule} from '@angular/forms';
import { RegisterComponent } from './Component/register/register.component'
import { HttpClientModule } from '@angular/common/http'
import { RequestService } from './Services/RequestService';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HowWorksComponent,
    LoginComponent,
    NotFoundComponent,
    NotFoundComponent,
    MenuComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [RequestService],
  bootstrap: [AppComponent]
})
export class AppModule { }
