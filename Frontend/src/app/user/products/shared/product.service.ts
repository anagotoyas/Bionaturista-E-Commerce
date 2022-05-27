import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Product } from './product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiBase: string = environment.apiBase;
  constructor( private http:HttpClient) {}

  getAllProducts(){
    return this.http.get<Product[]>(`${this.apiBase}/productos`);
  }
  getProductoById(idProducto:number){
    return this.http.get<Product[]>(`${this.apiBase}/productos/${idProducto}`);
  }
  
  agregarAlCarrito(idUsuario: number, idProducto: number){
    return this.http.put(`${this.apiBase}/usuarios/${idUsuario}/carrito/${idProducto}`, null)
  }
   
}
