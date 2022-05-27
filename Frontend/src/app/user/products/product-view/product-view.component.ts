import { Component, OnInit } from '@angular/core';
import { Product } from '../shared/product.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../shared/product.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  dataSource:Product;
  id:any;
  categoria: any;
  compuesto:any;
  precio:any;
  imagen:any;


  constructor(
    private activeRoute: ActivatedRoute,
    private productService: ProductService
  ) { }

  ngOnInit(): void {
    
    this.getInfoProducto();
    
  }

  getInfoProducto(){
    const params = this.activeRoute.snapshot.params;
    this.productService.getProductoById(params['id']).subscribe((data:any) => {
    this.dataSource = data['body'];
    this.imagen=data['body'].imagenP;
    console.log(this.imagen);
    this.categoria=data['body'].categoria.nombreCategoria;
    this.compuesto=data['body'].compuesto.nombreCompuesto;
    this.precio=data['body'].precioP.toFixed(2);


   });
    

  }

  agregarAlCarrito(idProducto:any){
   
    const ok = confirm('¿Estás seguro de agregar este producto al carrito de compras?');
    if(ok){
      this.productService.agregarAlCarrito(Number(sessionStorage.getItem('key')),idProducto ).subscribe(()=>{
       
      })
    }

  }

  
  
}

