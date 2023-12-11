import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { RequestService } from 'src/app/Services/RequestService';


@Component({
  selector: 'app-publish-form',
  templateUrl: './publish-form.component.html',
  styleUrl: './publish-form.component.scss'
})
export class PublishFormComponent {
submit() {
  throw new Error('Method not implemented.');
}

publisherForm!: FormGroup;
  ngOnInit(): void {
    this['publisherForm'] = new FormGroup({
      name: new FormControl(''),
      surname: new FormControl(''),
      username: new FormControl('', Validators.required),
      email: new FormControl(''),
      password: new FormControl(''),
      birthday: new FormControl('')
    });
  }
 
}
