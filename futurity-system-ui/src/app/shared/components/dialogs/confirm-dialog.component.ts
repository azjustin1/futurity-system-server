import { Component, Input } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { DialogAction } from '../../enums/dialog-action.enum';

@Component({
  selector: 'confirm-dialog',
  templateUrl: './confirm-dialog.component.html',
})
export class ConfirmDialogComponent {
  @Input() title?: string;
  @Input() content?: string;
  @Input() confirmText?: string = 'OK';
  @Input() cancelText?: string = 'Cancel';

  constructor(public dialogRef: MatDialogRef<ConfirmDialogComponent>) {}

  confirm(): void {
    this.dialogRef.close(DialogAction.CONFIRM);
  }

  cancel(): void {
    this.dialogRef.close(DialogAction.CANCEL);
  }
}
