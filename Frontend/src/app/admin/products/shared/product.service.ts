import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from 'src/app/user/products/shared/product.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiBase: string=environment.apiBase;

  constructor(private http:HttpClient) { }
  create(product: Product){
    console.log(product);

    return this.http.post(this.apiBase+'/productos',product);
  }
  edit(product: Product){
    console.log(product);

    return this.http.put(this.apiBase+'/productos', product);
  }
  delete(idProducto: number){
    return this.http.delete<Product>(this.apiBase+`/productos/${idProducto}`)
  }
  getAllProducts() {
    return this.http.get<Product[]>(`${this.apiBase}/productos`)
  }
  getProductById(idProducto: number){
    return this.http.get<Product>(this.apiBase+`/productos/${idProducto}`)
  }
  contarProducts(){
    return this.http.get<Object>(`${this.apiBase}/productos/contar`)
  }

}
