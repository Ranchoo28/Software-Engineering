import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogComponent } from '../Component/alert-publisher/alert-publisher.component';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class RequestService {
  
  loginUrl = "http://localhost:8080/api/login"
  registrationUrl = "http://localhost:8080/api/registration"
  dashboardUrl = "http://localhost:8080/api/dashboard"

  constructor(
    private http: HttpClient, 
    private router: Router,
    private dialog: MatDialog
    ) { }
    
  loginUtente(data: {}) : Observable<string>{
    return this.http.post<string>(this.loginUrl, data, httpOptions)
    
  }
 
  insertUtente(data: {}){
    return this.http.post(this.registrationUrl, data,httpOptions)
    .pipe(
      catchError(this.handleRegisterError)
    ).subscribe()

  }
  
  getDashboardData() {
    return this.http.get(this.dashboardUrl, httpOptions)
  }

  private handleRegisterError = (error: any) => {
    if(error.error.text === "Account registered!")
        this.apriConfirmAlert(error.error.text);
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
