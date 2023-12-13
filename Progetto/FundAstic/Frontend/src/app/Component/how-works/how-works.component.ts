import { Component } from '@angular/core';
import { CookiesUtils } from 'src/app/Utils/CookiesUtils';

@Component({
  selector: 'app-how-works',
  templateUrl: './how-works.component.html',
  styleUrls: ['./how-works.component.scss']
})
export class HowWorksComponent {
  isLogged!: boolean
  constructor(private cookieUtils: CookiesUtils){}
  ngOnInit(): void{
    this.isLogged = this.cookieUtils.checkLogged()
  } 
}
