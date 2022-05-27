import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Compuesto } from './compuesto.model';

@Injectable({
  providedIn: 'root'
})
export class CompuestoService {

  private apiBase:string = environment.apiBase;

  constructor(private http:HttpClient) { }

  crearCompuesto(compuesto: Compuesto) {
    return this.http.post(`${this.apiBase}/compuestos`, compuesto);
  }
  getAllCompuestos(){
    return this.http.get<Compuesto[]>(`${this.apiBase}/compuestos`)
  }

}
