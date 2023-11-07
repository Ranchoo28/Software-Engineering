import { Input, Component, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { FirebaseService } from 'src/app/Services/firebase.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {
  
  constructor(private firebase: FirebaseService){}
  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  }); 

  ngOnInit(){
   
  }
  
  submit() {  
   
  }
  
  @Input() error: string | null | undefined;

  @Output() submitEM = new EventEmitter();


}
