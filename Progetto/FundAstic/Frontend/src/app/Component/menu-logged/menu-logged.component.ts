import { Component } from '@angular/core';
import { ProxyService } from 'src/app/Services/ProxyService';
import {CookiesUtils} from 'src/app/Utils/CookiesUtils'
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { catchError, switchMap } from 'rxjs';

@Component({
  selector: 'app-menu-logged',
  templateUrl: './menu-logged.component.html',
  styleUrl: './menu-logged.component.scss'
})
export class MenuLoggedComponent {
  constructor(
    private proxyService: ProxyService, 
    private cookie: CookiesUtils, 
    private cookieService: CookieService,
    private router: Router
    ){}


  requestPermitAdmin() {
    // TODO
  }

  requestPermitFinanziatore() {
    this.proxyService.sendFinanziatoreRequest({
      username: this.cookie.getUsernameFromCookie(),
      action: "Finanzia",
      role: this.cookie.getRoleFromCookie()
    });
  }

  requestPermitPublisher() {
    return this.proxyService.sendPublisherRequest({
      username: this.cookie.getUsernameFromCookie(),
      action: "Pubblica",
      role: this.cookie.getRoleFromCookie()
    })

  }

  doLogout(): void{
    this.cookieService.deleteAll()
    this.router.navigate([""])
  }
}
