import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatDialogComponent } from '../alert-publisher/alert-publisher.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent {
  constructor
  (private dialog: MatDialog
    ){}
  denyAcces() {
    const c = this.dialog.open(MatDialogComponent, {
      data: { messaggio: 'Devi registrarti per poter accedere a questa funzione!' }
    });
  }
}
