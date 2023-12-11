import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'   // Specifica il tipo di contenuto
   // Se necessario, aggiungi un'intestazione di autorizzazione
  })
};
@Injectable({
  providedIn: 'root'
})
export class RequestService {
  
  loginUrl = "http://localhost:8080/api/login"
  registrationUrl = "http://localhost:8080/api/registration"
  dashboardUrl = "http://localhost:8080/api/dashboard"

  constructor(private http: HttpClient, private router: Router) { }
  loginUtente(data: {}) : Observable<string>{
    return this.http.post<string>(this.loginUrl, data, httpOptions)
  }
 
  insertUtente(data: {}){
    return this.http.post(this.registrationUrl, data,httpOptions).subscribe()
  }
  
  getDashboardData() {
    return this.http.get(this.dashboardUrl, httpOptions)
  }

}
