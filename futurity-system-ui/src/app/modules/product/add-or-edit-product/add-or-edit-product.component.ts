import { Component, Input, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Product } from '../models/product.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'add-or-edit-product',
  templateUrl: './add-or-edit-product.component.html',
  styleUrls: ['./add-or-edit-product.component.css'],
})
export class AddOrEditProductComponent implements OnInit {
  @Input() product: Product = {
    name: '',
    description: '',
    price: undefined,
    quantity: undefined,
  };
  @Input() actionText: string = 'Add';

  productForm!: FormGroup;

  constructor(
    public dialogRef: MatDialogRef<AddOrEditProductComponent>,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.productForm = this.formBuilder.group({
      name: [this.product.name, Validators.required],
      description: [this.product.description],
      price: [this.product.price, Validators.required],
      quantity: [this.product.quantity, Validators.required],
    });
  }

  onSubmit(): void {
    if (this.productForm.valid) {
      this.dialogRef.close(this.product);
    }
  }

  onCancel() {
    this.dialogRef.close();
  }
}
