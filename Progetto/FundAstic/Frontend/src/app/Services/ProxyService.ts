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
export class ProxyService {
  
  menuProtectedUrl = "http://localhost:8080/api/checkPermission"

  constructor(
    private http: HttpClient, 
    private router: Router,
    private dialog: MatDialog
    ) { }

    apriAlert(): void {
      const c = this.dialog.open(MatDialogComponent, {
        data: { messaggio: 'Accesso negato!' }
      });
    }
  
  sendPublisherRequest(data: {}){
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleError),
      tap(() => this.router.navigate(["publish-project"]))
    )
    .subscribe()
  }

  sendFinanziatoreRequest(data: {}) {
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleError),
      tap(() => this.router.navigate(["finance-project"]))
    )
    .subscribe()
  }

  sendModRequest(data: {}) {
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleError),
      tap(() => this.router.navigate(["mod-project"]))
    )
    .subscribe()
  }

  private handleError = (error: any) => {
    this.apriAlert();
    return throwError('Accesso negato!');
  }

}
