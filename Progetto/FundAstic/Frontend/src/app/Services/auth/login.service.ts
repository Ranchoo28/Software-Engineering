import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  isLoggedIn = false

  constructor() { }

  isAuthenticated(){
    return this.isLoggedIn
  }
}
