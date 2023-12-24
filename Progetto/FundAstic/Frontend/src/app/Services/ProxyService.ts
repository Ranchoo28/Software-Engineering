import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { tap, catchError } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogComponent } from '../Component/alert-publisher/alert-publisher.component';
import { ProjectService } from './ProjectService';

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
    private dialog: MatDialog,
    private projectService: ProjectService
    ) { }

      apriAlert(body: string): void {
      const c = this.dialog.open(MatDialogComponent, {
        data: { messaggio: body }
      });
    }
  
  sendPublisherRequest(data: {}){
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleErrorPub),
      tap(() => this.router.navigate(["publish-project"]))
    )
    .subscribe()
  }

  sendFinanziatoreRequest(data: {}) {
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleErrorFin),
      tap(() => this.router.navigate(["finance-project"]))
    )
    .subscribe()
  }

  sendModRequest(data: {}) {
    this.http.post(this.menuProtectedUrl, data, httpOptions)
    .pipe(
      catchError(this.handleErrorMod),
      tap(() => this.projectService.deleteProject(data))
    )
    .subscribe()
  }

  private handleErrorPub = (error: any) => {
    this.apriAlert("Devi essere publisher per poter accedere a questa funzione.");
    return throwError('Accesso negato!');
  }

  private handleErrorFin = (error: any) => {
    this.apriAlert("Devi essere finanziatore per poter accedere a questa funzione.");
    return throwError('Accesso negato!');
  }

  private handleErrorMod = (error: any) => {
    this.apriAlert("Devi essere moderatore per poter accedere a questa funzione.");
    return throwError('Accesso negato!');
  }

}
