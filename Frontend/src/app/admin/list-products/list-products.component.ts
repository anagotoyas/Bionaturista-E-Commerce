import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Product } from 'src/app/user/products/shared/product.model';
import { ProductService } from '../products/shared/product.service';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrls: ['./list-products.component.css']
})
export class ListProductsComponent implements OnInit {

  displayedColumns: string[] = [ 'id', 'nombre', 'stock', 'precio', 'categoria', 'acciones'];
  dataSource: MatTableDataSource<Product>;
  nroProductos = 0;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.getProducts();
    this.contarProductos();
  }

  getProducts(){
    this.productService.getAllProducts().subscribe((data:any) => {
      this.dataSource = new MatTableDataSource(data['body']);
      
    })
  }

  contarProductos(){
    this.productService.contarProducts().subscribe((data: any) => {
      this.nroProductos = data['body'];
    })
  }

  applyFilter(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  redirigir(){
    window.location.replace("admin/productos/crear");
  }

}
