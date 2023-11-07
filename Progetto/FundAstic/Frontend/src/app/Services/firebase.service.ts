import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FirebaseService {

  constructor(private http: HttpClient) { }
  insertUtente(url: string, data: {}){
    return this.http.post(url, data)
  }
}
