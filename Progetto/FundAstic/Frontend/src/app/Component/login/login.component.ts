import { Input, Component, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RequestService } from 'src/app/Services/RequestService';
import { Router } from '@angular/router';
import { tap, switchMap, catchError } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { throwError } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogComponent } from '../alert-publisher/alert-publisher.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {

  loginForm!: FormGroup
  constructor(
    private request: RequestService, 
    private router: Router, 
    private cookie: CookieService,
    private dialog: MatDialog
    ){}
 
  ngOnInit():void{
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    }); 
  }
  
  submit() {
    this.request.loginUtente({
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    })
    .pipe(
      tap((token: string) => {
        token = JSON.stringify(token)
        const helper = new JwtHelperService();
        const decodedToken = helper.decodeToken(token);
        const expirationDate = helper.getTokenExpirationDate(token);
        const allKeys = Object.keys(decodedToken);

        if (expirationDate) {
          const date = new Date(expirationDate.toUTCString());
          this.cookie.set('SESSION', token, { sameSite: 'None', secure: true, expires:date});
          this.cookie.set('ROLE', decodedToken[allKeys[3]], { sameSite: 'None', secure: true, expires:date})
        } else {
          console.error('La data di scadenza non è disponibile nel token.');
        }
      }),
      switchMap(() => this.router.navigate([''])),
      catchError(this.handleLoginError)
    )
    .subscribe();
  }
  
  private handleLoginError = (error: any) => {
    console.log(error.error.text)
    if(error.error.text === "Account registered!")
        this.apriErrorAlert(error.error.text);
    else
        this.apriErrorAlert(error.error);
    
    return throwError(error)
  }

  apriErrorAlert(action: string): void {
    this.dialog.open(MatDialogComponent, {
        data: { messaggio: action }
    });
  }

  apriConfirmAlert(action: string): void{
      this.dialog.open(MatDialogComponent, {
          data: { messaggio: action }
      });
      this.router.navigate([""])
  }
}

