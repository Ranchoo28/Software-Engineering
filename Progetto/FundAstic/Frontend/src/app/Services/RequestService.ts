import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

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

  constructor(private http: HttpClient) { }
  loginUtente(data: {}) {
    return this.http.post(this.loginUrl, data, httpOptions).subscribe()
  }
 
  insertUtente(data: {}){
    return this.http.post(this.registrationUrl, data,httpOptions).subscribe()
  }
  
  getDashboardData() {
    return this.http.get(this.dashboardUrl, httpOptions)
  }

}
