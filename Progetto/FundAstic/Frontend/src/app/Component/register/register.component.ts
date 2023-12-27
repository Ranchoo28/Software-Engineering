import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { RequestService } from 'src/app/Services/RequestService';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent {

  regexEmail = "^[a-zA-Z0-9.!#$%&’*+/=?^_{|}~-]+@(?:gmail\.com|yahoo\.com|hotmail\.com|libero\.it|icloud\.com|gmx\.com|aol\.com)"
  regexPassword = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@!%~&£°#'?*=.])[a-zA-Z0-9@!%&£°#'?*=.]{8,}"
  regexFirstLast = "^(?=.{2,20}$)[a-zA-Z]+(?:[-'\\s][a-zA-Z]+)*$"
  registerForm!: FormGroup;
  tooltipPassword = "La password deve essere di almeno 8 caratteri, di cui una lettera maiuscola e un carattere speciale."

  constructor(private request: RequestService){}

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      name: new FormControl('',[(c: AbstractControl) => Validators.required(c), Validators.pattern(this.regexFirstLast)]),
      surname: new FormControl('',[(c: AbstractControl) => Validators.required(c), Validators.pattern(this.regexFirstLast)]),
      username: new FormControl('', Validators.required),
      email: new FormControl('',[(c: AbstractControl) => Validators.required(c), Validators.pattern(this.regexEmail)]),
      password: new FormControl('',[(c: AbstractControl) => Validators.required(c), Validators.pattern(this.regexPassword)]),
      birthday: new FormControl('', Validators.required),
      number: new FormControl('', Validators.required),
      role: new FormControl('', Validators.required)
    });
  }
 
  
  submit() {
    this.request.insertUtente({
      name: this.registerForm.value.name,
      surname: this.registerForm.value.surname,
      username: this.registerForm.value.username,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      birthday: this.registerForm.value.birthday,
      number: this.registerForm.value.number,
      role: this.registerForm.value.role
    })
  }

}
