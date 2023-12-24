import { Component } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ProjectService } from 'src/app/Services/ProjectService';
import { CookiesUtils } from 'src/app/Utils/CookiesUtils';
import { MatDialogComponent } from '../alert-publisher/alert-publisher.component';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';

const MAX_FILE_SIZE_KB = 100;

@Component({
  selector: 'app-publish-form',
  templateUrl: './publish-form.component.html',
  styleUrls: ['./publish-form.component.scss']
})
export class PublishFormComponent {
  byteArrayImage!: number[];
  byteArrayVideo!: number[];
  byteArrayDoc!: number[];

  form: FormGroup;
  firstForm: FormGroup;
  secondForm: FormGroup;
  paymentForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private projectService: ProjectService,
    private cookieUtils: CookiesUtils,
    private dialog: MatDialog,
    private router: Router
  ) {

    if (!cookieUtils.checkLogged() || cookieUtils.getRoleFromCookie() === "Utente" || cookieUtils.getRoleFromCookie() === "Finanziatore") {
      const c = this.dialog.open(MatDialogComponent, {
        data: { messaggio: 'Non provare a entrare cambiando url :P' }
      });
      this.router.navigate([''])
    }

    this.form = this.formBuilder.group({});
    this.firstForm = this.formBuilder.group({
      projectName: ['FundAstic', Validators.required],
      projectDescrip: ['Descrizione', Validators.required],
      projectCategory: ['Informatica', Validators.required],
      email: ['savcreaa.kr98@gmail.com', Validators.required],
      imageProject: [],
      videoProject: [],
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

  removeMember() {
    this.projectMembers.removeAt(this.projectMembers.length - 1);
  }

  getFirstNameControl(memberGroup: AbstractControl): FormControl {
    return memberGroup.get('firstName') as FormControl;
  }

  getLastNameControl(memberGroup: AbstractControl): FormControl {
    return memberGroup.get('lastName') as FormControl;
  }

  submit() {
    var formattedArray = this.secondForm.get('projectMembers')?.value.map(
      (person: { firstName: string, lastName: string }) => `${person.firstName} ${person.lastName}`);

    this.projectService.publishProject({
      title: this.firstForm.value.projectName,
      description: this.firstForm.value.projectDescrip,
      category: this.firstForm.value.projectCategory,
      email: this.firstForm.value.email,
      image: this.byteArrayImage,
      video: this.byteArrayVideo,
      amount: this.firstForm.value.projectAmount,
      startDate: this.firstForm.value.startProjectDate,
      endDate: this.firstForm.value.endProjectDate,
      members: formattedArray,
      doc_ricon: this.byteArrayDoc,
      payments_method: `${this.paymentForm.value.paymentMethod}: ${this.paymentForm.value.iban}: ${this.paymentForm.value.paypalEmail}`,
    });
  }

  uploadAndConvertImage($event: Event) {
    console.log("entra img")
    this.uploadAndConvertFile($event, 'image');
  }

  uploadAndConvertVideo($event: Event) {
    console.log("entra video")
    this.uploadAndConvertFile($event, 'video');
  }

  uploadAndConvertDoc($event: Event) {
    this.uploadAndConvertFile($event, 'doc');
  }

  private uploadAndConvertFile($event: Event, fileType: 'image' | 'video' | 'doc') {
    const element = $event?.currentTarget as HTMLInputElement;
    const fileList: FileList | null = element.files;
  
    if (fileList) {
      const file: File | null = fileList.item(0);
  
      if (file) {
        const fileSizeInKB = file.size / 1024;
  
        if (fileSizeInKB > MAX_FILE_SIZE_KB) {
          console.error(`File size exceeds the maximum allowed size of ${MAX_FILE_SIZE_KB} KB`);
          this.dialog.open(MatDialogComponent, {
            data: { messaggio: "Il file deve essere minore di 100Kb" }
          });
  
          switch (fileType) {
            case 'image':
              this.byteArrayImage = [];
              this.firstForm.get('imageProject')?.reset();
              break;
            case 'video':
              this.byteArrayVideo = [];
              this.firstForm.get('videoProject')?.reset();
              break;
            case 'doc':
              this.byteArrayDoc = [];
              this.secondForm.value.doc.reset()
              break;
          }
  
          return;
        }
  
        const reader = new FileReader();
  
        reader.onload = (event?: any) => {
          const resultArrayBuffer: ArrayBuffer | null = event.target.result;
  
          if (resultArrayBuffer) {
            const byteArray: Uint8Array = new Uint8Array(resultArrayBuffer);
            const newArray: number[] = Array.from(byteArray);
  
            switch (fileType) {
              case 'image':
                this.byteArrayImage = newArray;
                this.firstForm.get('imageProject')?.setValue(newArray);
                break;
              case 'video':
                this.byteArrayVideo = newArray;
                this.firstForm.get('videoProject')?.setValue(newArray);
                break;
              case 'doc':
                this.byteArrayDoc = newArray;
                this.secondForm.get('docProject')?.setValue(newArray);
                break;
            }
          } else {
            console.error("Error reading file as ArrayBuffer");
          }
        };
  
        reader?.readAsArrayBuffer(file);
      }
    }
  }
  
  
}
