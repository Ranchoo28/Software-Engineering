import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';

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

  constructor(private http: HttpClient, private router: Router) { }
  
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
      tap(() => this.router.navigate(["finace-project"]))
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

  private handleError(error: any) {
    console.error('An error occurred:', error);
    return throwError('Accesso negato!');
}

}
