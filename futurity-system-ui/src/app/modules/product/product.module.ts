import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import { AddOrEditProductComponent } from './add-or-edit-product/add-or-edit-product.component';
import { ProductComponent } from './product.component';
import { ProductService } from './product.service';
import { MatIconModule } from '@angular/material/icon';
import { ConfirmDialogComponent } from 'src/app/shared/components/dialogs/confirm-dialog.component';

@NgModule({
  declarations: [
    ProductComponent,
    AddOrEditProductComponent,
    ConfirmDialogComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    MatTableModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
  ],
  providers: [ProductService],
})
export class ProductModule {}
