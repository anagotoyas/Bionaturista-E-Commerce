import { Component, OnInit } from '@angular/core';

import { Product } from '../shared/product.model';
import { ProductService } from '../shared/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  dataSource: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getAllProducts();
    
  }

  getAllProducts(){
    this.productService.getAllProducts().subscribe((data:any)=>{
      this.dataSource = data['body'];
      console.log(data);
    });
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  // dataSource1 : MatTableDataSource <Product>;
  // displayedColumns: string[] = ['idProducto','nombreP', 'precioP', 'Ver' ];
  // constructor(
  //   private productService: ProductService
  // ) { }

  // ngOnInit(): void {
  //   this.getAllProducts();
    
  // }

  // getAllProducts(){
  //   this.productService.getAllProducts().subscribe((data: any)=>{
  //     console.log(data['body']);
  //     this.dataSource1 = new MatTableDataSource (data['body']);
  //   })
  // }
  // applyFilter(value: string) {
  //   this.dataSource1.filter = value.trim().toLowerCase();
  // }

}
