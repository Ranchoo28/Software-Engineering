import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';


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
