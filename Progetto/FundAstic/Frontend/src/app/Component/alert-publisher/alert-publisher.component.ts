import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mat-dialog',
  templateUrl: './alert-publisher.component.html',
  styleUrl: './alert-publisher.component.scss'
})

export class MatDialogComponent {
  constructor(
    private dialogRef: MatDialogRef<MatDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }
  
  closeAlert(): void {
    this.dialogRef.close();
  }
}
