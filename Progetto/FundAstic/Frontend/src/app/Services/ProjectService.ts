import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { tap, catchError } from 'rxjs/operators';
import { MatDialogComponent } from '../Component/alert-publisher/alert-publisher.component';
import { throwError } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
};

@Injectable({
    providedIn: 'root'
})

export class ProjectService{
    private url: string = "http://localhost:8080/api/project/publish"

    constructor(
        private http: HttpClient, 
        private router: Router,
        private dialog: MatDialog
        ){}

    publishProject(data: {}){
        return this.http.post(this.url, data, httpOptions)
        .pipe(
            catchError(this.handleError)
       
        ).subscribe()
    }

    private handleError = (error: any) => {
        if(error.error.text === "Project published successfully!")
            this.apriPublishAlert();
        else
            this.apriErrorAlert();
        
        return throwError(error)
    }

    apriErrorAlert(): void {
        const c = this.dialog.open(MatDialogComponent, {
          data: { messaggio: 'Problemi nella pubblicazione del progetto, riprova.' }
        });
    }

    apriPublishAlert(): void{
        const c = this.dialog.open(MatDialogComponent, {
            data: { messaggio: 'Progetto pubblicato con successo!' }
        });
        this.router.navigate(["menu-logged"])
    }
    
}