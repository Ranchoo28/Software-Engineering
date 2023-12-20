import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-finace-form',
  templateUrl: './finance-form.component.html',
  styleUrl: './finance-form.component.scss'
})
export class FinanceFormComponent {
  
 
  firstFormGroup!: FormGroup;

  constructor(private formBuilder: FormBuilder){}

  ngOnInit(): void {
    this.firstFormGroup = this.formBuilder.group({
      donationAmount: [''],
      paymentMethod: [''],
      paypalEmail: [''],
      creditCardNumber: [''],
      creditCardExpiration: [''],
      creditCardCvv: [''],
      creditCardName: [''],
    });
  }

  get selectedPaymentMethod() {
    const paymentMethod = this.firstFormGroup.get('paymentMethod');
    return paymentMethod ? paymentMethod.value : null;
  }

  
submit() {
  throw new Error('Method not implemented.');
}

}
