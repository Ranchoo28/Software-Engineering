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
   
    private publishUrl: string = "http://localhost:8080/api/project/publish"
    private financeUrl: string = "http://localhost:8080/api/project/finance"
    private projectsUrl: string = "http://localhost:8080/api/showcase"
    private deleteUrl: string = "http://localhost:8080/api/project/delete"

    constructor(
        private http: HttpClient, 
        private router: Router,
        private dialog: MatDialog
        ){}
        
    getProjects(){
       return this.http.get(this.projectsUrl, httpOptions)
    }

    financeProject(data: {}){
        return this.http.post(this.financeUrl, data, httpOptions)
        .pipe(
            catchError(this.handleErrorFinance)
        ).subscribe()
    }

    publishProject(data: {}){
        return this.http.post(this.publishUrl, data, httpOptions)
        .pipe(
            catchError(this.handleErrorPublish)
        ).subscribe()
    }

    deleteProject(data: {}){
        return this.http.post(this.deleteUrl, data, httpOptions)
        .pipe(
            catchError(this.handleErrorDelete)
        ).subscribe()
    }

    private handleErrorDelete = (error: any) => {
        if(error.error.text === "Project removed successfully!")
            this.apriPublishAlert("Progetto rimosso");
        else
            this.apriErrorAlert("nella rimozione");
        
        return throwError(error)
    }

    private handleErrorFinance = (error: any) => {
        if(error.error.text === "Project financed successfully!")
            this.apriPublishAlert("Finanziamento avvenuto");
        else
            this.apriErrorAlert("nel finanziamento");
        
        return throwError(error)
    }

    private handleErrorPublish = (error: any) => {
        if(error.error.text === "Project published successfully!")
            this.apriPublishAlert("Progetto pubblicato ");
        else
            this.apriErrorAlert(" durante la pubblicazione");
        
        return throwError(error)
    }


    apriErrorAlert(action: string): void {
        this.dialog.open(MatDialogComponent, {
            data: { messaggio: "Errore " + action + ", riprova" }
        });
    }

    apriPublishAlert(action: string): void{
        this.dialog.open(MatDialogComponent, {
            data: { messaggio: action + ' con successo!' }
        });
        this.router.navigate([""])
    }
    
}