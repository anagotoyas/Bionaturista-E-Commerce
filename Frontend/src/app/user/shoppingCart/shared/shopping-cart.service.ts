
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../products/shared/product.model';


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private apiBase: string = environment.apiBase

  constructor(private http:HttpClient) { }

  verCarrito(idUsuario: number){
    return this.http.get<Product[]>( `${this.apiBase}/usuarios/${idUsuario}/carrito`)
  }
  eliminarDelCarrito(idUsuario: number,idProducto:number){
    return this.http.delete<Product[]>( `${this.apiBase}/usuarios/${idUsuario}/carrito/${idProducto}`)
  }


}
