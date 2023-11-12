import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Product } from './models/product.model';
import { ProductService } from './product.service';
import { AddOrEditProductComponent } from './add-or-edit-product/add-or-edit-product.component';
import { ConfirmDialogComponent } from 'src/app/shared/components/dialogs/confirm-dialog.component';
import { DialogAction } from 'src/app/shared/enums/dialog-action.enum';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent {
  products: Product[] = [];

  displayedColumns: string[] = [
    'id',
    'name',
    'description',
    'price',
    'quantity',
    'action',
  ];

  constructor(
    private productService: ProductService,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.productService.getAll().subscribe((data: Product[]) => {
      this.products = data;
    });
  }

  onAddNewProduct() {
    const dialogRef = this.openModal();
    dialogRef.afterClosed().subscribe((product) => {
      console.log(product);
      if (product) {
        this.productService.create(product).subscribe((newProduct) => {
          this.products = [...this.products, newProduct];
        });
      }
    });
  }

  onEdit(product: Product) {
    const dialogRef = this.openModal(product);
    dialogRef.componentInstance.product = product;
    dialogRef.componentInstance.actionText = 'Edit';
    dialogRef.afterClosed().subscribe((product) => {
      if (product) {
        this.productService.update(product.id, product).subscribe((res) => {
          this.products = [...this.products];
        });
      }
    });
  }

  onDelete(productId: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      disableClose: true,
    });

    dialogRef.componentInstance.title = 'Warning';
    dialogRef.componentInstance.content =
      'Are you sure to delete this product?';

    dialogRef.afterClosed().subscribe((result) => {
      if (result === DialogAction.CONFIRM) {
        this.productService.delete(productId).subscribe((res) => {
          if (res) {
            this.products = this.products.filter(
              (product) => product.id != productId
            );
          }
        });
      } else {
        //  Do some thing here
      }
    });
  }

  openModal(product?: Product) {
    const dialogRef = this.dialog.open(AddOrEditProductComponent, {
      width: '500px',
      disableClose: true,
      data: product,
    });
    return dialogRef;
  }
}
