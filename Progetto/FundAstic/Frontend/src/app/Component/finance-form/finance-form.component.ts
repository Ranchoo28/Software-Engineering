import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-finace-form',
  templateUrl: './finance-form.component.html',
  styleUrl: './finance-form.component.scss'
})
export class FinanceFormComponent {
  finaceForm!: FormGroup;
  ngOnInit(): void {
    this['finaceForm'] = new FormGroup({
      name: new FormControl(''),
      surname: new FormControl(''),
      username: new FormControl('', Validators.required),
      email: new FormControl(''),
      password: new FormControl(''),
      birthday: new FormControl('')
    });
  }
submit() {
  throw new Error('Method not implemented.');
}

}
