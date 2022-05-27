import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Product } from '../../products/shared/product.model';
import { ShoppingCartService } from '../shared/shopping-cart.service';

@Component({
  selector: 'app-cart-view',
  templateUrl: './cart-view.component.html',
  styleUrls: ['./cart-view.component.css']
})
export class CartViewComponent implements OnInit {
  dataSource : MatTableDataSource <Product>;
  displayedColumns: string[] = ['Imagen','Producto', 'Eliminar', 'Precio' ];
  precioPro: any
  delivery: any
  precioTotal:any;
  preciot:any;


  constructor(
    private carritoService: ShoppingCartService
  ) { }

  ngOnInit(): void {
    this.verProductosCarrito();
  }

  verProductosCarrito(){
    this.carritoService.verCarrito(Number(sessionStorage.getItem('key'))).subscribe((data:any) => {
      this.dataSource = data;
      this.preciot=data.precioP;
      var precio=0;
     
      for(let i=0; i< data.length; i++){
       
        precio += data[i].precioP ;
        console.log(precio);
      }
      this.precioPro=Number(precio).toFixed(2);
      this.delivery=Number(7);
      this.delivery=this.delivery.toFixed(2);
      this.precioTotal=(Number(this.precioPro)+Number(this.delivery)).toFixed(2);
      
    });

    
    


  }
  
      
  eliminarDelCarrito(idProducto:number){
    const ok = confirm('¿Estás seguro de eliminar este producto al carrito de compras?');
    if(ok){
      this.carritoService.eliminarDelCarrito(Number(sessionStorage.getItem('key')),idProducto).subscribe(()=>{
        window.location.reload()
      })
       
      }
    }
    
  }


