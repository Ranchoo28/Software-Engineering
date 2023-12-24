import { Component } from '@angular/core';
import { ProxyService } from 'src/app/Services/ProxyService';
import {CookiesUtils} from 'src/app/Utils/CookiesUtils'
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';


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

  requestPermitPublisher() {
    return this.proxyService.sendPublisherRequest({
      username: this.cookie.getUsernameFromCookie(),
      action: "Pubblica",
      role: this.cookie.getRoleFromCookie()
    })

  }

  doLogout(): void{
    this.cookieService.deleteAll()
    this.router.navigate([""]).then(() => {
      location.reload()
    })
  }
}
