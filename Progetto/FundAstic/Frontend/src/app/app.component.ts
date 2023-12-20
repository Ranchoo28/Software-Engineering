import { Component } from '@angular/core';
import { CookiesUtils } from './Utils/CookiesUtils';
import { Router } from '@angular/router';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'FundAstic';

  isLogged!: boolean
  
  constructor(private cookieUtils: CookiesUtils, private router: Router ){
   
  }

  ngOnInit(): void {
    this.isLogged = this.cookieUtils.checkLogged()
    const username = this.cookieUtils.getUsernameFromCookie()
   
  }
  
  
}


