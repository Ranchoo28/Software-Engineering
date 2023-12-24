import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/Services/ProjectService';
import { SendTitleService } from 'src/app/Services/SendTitleService';
import { CookiesUtils } from 'src/app/Utils/CookiesUtils';
import { MatDialogComponent } from '../alert-publisher/alert-publisher.component';

@Component({
  selector: 'app-finace-form',
  templateUrl: './finance-form.component.html',
  styleUrl: './finance-form.component.scss'
})
export class FinanceFormComponent {
  
 
  firstFormGroup!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private getTitleService: SendTitleService,
    private projectService: ProjectService,
    private cookieUtils: CookiesUtils,
    private dialog: MatDialog,
    private router: Router
    ){
      if(!cookieUtils.checkLogged() || cookieUtils.getRoleFromCookie() === "Utente" || cookieUtils.getRoleFromCookie() === "Finanziatore"){
        const c = this.dialog.open(MatDialogComponent, {
          data: { messaggio: 'Non provare a entrare cambiando url :P' }
        });
        this.router.navigate([''])
      }
    }

  ngOnInit(): void {
    this.firstFormGroup = this.formBuilder.group({
      donationAmount: [''],
      paymentMethod: [''],
      paypalEmail: [''],
      ibanCode:['']
    });
  }

  get selectedPaymentMethod() {
    const paymentMethod = this.firstFormGroup.get('paymentMethod');
    return paymentMethod ? paymentMethod.value : null;
  }

  submit() {
    this.projectService.financeProject({
      title: this.getTitleService.title,
      username: this.cookieUtils.getUsernameFromCookie(),
      donation_amount: this.firstFormGroup.value.donationAmount,
      payment_method: this.firstFormGroup.value.paymentMethod +
      " " + this.firstFormGroup.value.ibanCode +
      " " + this.firstFormGroup.value.paypalEmail
    })
  }
}
