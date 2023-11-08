import { Input, Component, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RequestService } from 'src/app/Services/RequestService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent {

  loginForm!: FormGroup
  constructor(private request: RequestService){}
 
  ngOnInit():void{
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    }); 
  }
  
  submit() {
    this.request.loginUtente({
      username: this.loginForm.value.username,
      password: this.loginForm.value.password
    })
  }
  


  @Output() submitEM = new EventEmitter();


}
