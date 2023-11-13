import { Component } from '@angular/core';
import { RequestService } from 'src/app/Services/RequestService';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {  
  project = "";
  users = "";
  investment = "";
  eur = "";
  
  data: any
  constructor(private request: RequestService){}

  ngOnInit(): void {
    this.request.getDashboardData().subscribe((result: any) => {
      this.data = Object.keys(result).map((key) => { return result[key] })
      
      this.users = this.data[0].userActiveNumber
      this.project = this.data[0].projectActiveAmount
      this.investment = this.data[0].donationNumber
      this.eur = this.data[0].eurAmount 
    });
    
    
    
  }
}
