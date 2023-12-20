import { Component } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectService } from 'src/app/Services/ProjectService';
import * as TextEncoding from 'text-encoding-utf-8';
@Component({
  selector: 'app-publish-form',
  templateUrl: './publish-form.component.html',
  styleUrls: ['./publish-form.component.scss']
})

export class PublishFormComponent {
  byteArrayImage!: number[]
  byteArrayVideo!: number[]
  byteArrayDoc!: number[]

  form: FormGroup;
  firstForm: FormGroup;
  secondForm: FormGroup;
  paymentForm: FormGroup;

 constructor(private formBuilder: FormBuilder, private projectService: ProjectService) { 
    this.form = this.formBuilder.group({})
    this.firstForm = this.formBuilder.group({
      projectName: ['FundOstic', Validators.required],
      projectDescrip: ['Sium', Validators.required],
      projectCategory: ['Gay', Validators.required],
      imageProject: [this.byteArrayImage, Validators.required],
      videoProject: [this.byteArrayVideo, Validators.required],
      projectAmount: ['150', Validators.required],
      startProjectDate: ['', Validators.required],
      endProjectDate: ['', Validators.required],
    });

    this.secondForm = this.formBuilder.group({
      projectMembers: this.formBuilder.array([]),
    });

    this.paymentForm = this.formBuilder.group({
      paymentMethod: ['', Validators.required],
      iban: ['65y676', Validators.required],
      paypalEmail: ['', Validators.required],
    });

    
   
  }

  get projectMembers() {
    return this.secondForm.get('projectMembers') as FormArray;
  }

  get selectedPaymentMethod() {
    const paymentMethod = this.paymentForm.get('paymentMethod');
    return paymentMethod ? paymentMethod.value : null;
  }

  
  addMember() {
    const memberFormGroup = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
    });

    this.projectMembers.push(memberFormGroup);
  }

  removeMember(index: number) {
    this.projectMembers.removeAt(index);
  }

  getFirstNameControl(memberGroup: AbstractControl): FormControl {
    return memberGroup.get('firstName') as FormControl;
  }
  
  getLastNameControl(memberGroup: AbstractControl): FormControl {
    return memberGroup.get('lastName') as FormControl;
  }

  submit() { 
    var formattedArray = this.secondForm.get('projectMembers')?.value.map(
      (person: { firstName: string, lastName: string, doc: number[] }) => `${person.firstName} ${person.lastName} ${person.doc}`);
      
    console.log(formattedArray)
    this.projectService.publishProject({
      title: this.firstForm.get('projectName')?.value,
      description: this.firstForm.get('projectDescrip')?.value,
      category: this.firstForm.get('projectCategory')?.value,
      image: this.byteArrayImage,
      video: this.byteArrayVideo,
      amount: this.firstForm.get('projectAmount')?.value,
      startDate: this.firstForm.get('startProjectDate')?.value,
      endDate: this.firstForm.get('endProjectDate')?.value,
      members: formattedArray,
      doc_ricon: this.byteArrayDoc,
      payments_method: this.paymentForm.get('paymentMethod')?.value 
      + ": " + this.paymentForm.get('iban')?.value 
      + ": " + this.paymentForm.get('paypalEmail')?.value,
    })
  }

  uploadAndConvertImage($event: Event) {
    const element = $event?.currentTarget as HTMLInputElement;
    const fileList: FileList | null = element.files;

    if (fileList) {
      const file: File | null = fileList.item(0);

      if (file) {
        const reader = new FileReader();

        reader.onload = (event?: any) => {
          const resultArrayBuffer: ArrayBuffer | null = event.target.result;

          if (resultArrayBuffer) {
            const byteArray: Uint8Array = new Uint8Array(resultArrayBuffer);
            const newArray: number[] = Array.from(byteArray);
            this.byteArrayImage = newArray;
          } else {
            console.error("Error reading file as ArrayBuffer");
          }
        };

        reader?.readAsArrayBuffer(file);
      }
    }
  }

  uploadAndConvertVideo($event: Event) {
    const element = $event?.currentTarget as HTMLInputElement;
    const fileList: FileList | null = element.files;

    if (fileList) {
      const file: File | null = fileList.item(0);

      if (file) {
        const reader = new FileReader();

        reader.onload = (event?: any) => {
          const resultArrayBuffer: ArrayBuffer | null = event.target.result;

          if (resultArrayBuffer) {
            const byteArray: Uint8Array = new Uint8Array(resultArrayBuffer);
            const newArray: number[] = Array.from(byteArray);
            this.byteArrayVideo = newArray;
          } else {
            console.error("Error reading file as ArrayBuffer");
          }
        };

        reader?.readAsArrayBuffer(file);
      }
    }
  }

  uploadAndConvertDoc($event: Event) {
    const element = $event?.currentTarget as HTMLInputElement;
    const fileList: FileList | null = element.files;

    if (fileList) {
      const file: File | null = fileList.item(0);

      if (file) {
        const reader = new FileReader();

        reader.onload = (event?: any) => {
          const resultArrayBuffer: ArrayBuffer | null = event.target.result;

          if (resultArrayBuffer) {
            const byteArray: Uint8Array = new Uint8Array(resultArrayBuffer);
            const newArray: number[] = Array.from(byteArray);
            this.byteArrayDoc = newArray;
          } else {
            console.error("Error reading file as ArrayBuffer");
          }
        };

        reader?.readAsArrayBuffer(file);
      }
    }
  }

}
