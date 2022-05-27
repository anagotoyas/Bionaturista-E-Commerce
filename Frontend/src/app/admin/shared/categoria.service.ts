import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Categoria } from 'src/app/admin/shared/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  private apiBase:string = environment.apiBase+"/categorias";

  constructor(private http:HttpClient) { }

  getAllCategorias(){
    return this.http.get<Categoria[]>(this.apiBase)
  }

  crearCategoria(categoria: Categoria) {
    return this.http.post(this.apiBase, categoria);
  }
}
