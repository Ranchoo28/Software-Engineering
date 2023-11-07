import { Component, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FirebaseService } from 'src/app/Services/firebase.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  constructor(private firebase: FirebaseService){}
  registerForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    email: new FormControl('',[Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
  });

  submit() {
    this.firebase.insertUtente(
      "https://fundastic-cd320-default-rtdb.europe-west1.firebasedatabase.app/user.json",
      {
        username: this.registerForm.value.username, 
        email: this.registerForm.value.email, 
        password: this.registerForm.value.password
      }).subscribe();
  }

  @Output() submitEM = new EventEmitter();
}
