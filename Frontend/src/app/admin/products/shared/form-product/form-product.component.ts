import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Categoria } from 'src/app/admin/shared/categoria.model';
import { CategoriaService } from 'src/app/admin/shared/categoria.service';
import { Compuesto } from 'src/app/admin/shared/compuesto.model';
import { CompuestoService } from 'src/app/admin/shared/compuesto.service';
import { Product } from 'src/app/user/products/shared/product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-form-product',
  templateUrl: './form-product.component.html',
  styleUrls: ['./form-product.component.css']
})

export class FormProductComponent implements OnInit {
  form:FormGroup;

  @Input() product: Product = new Product();
  @Output() onSubmit: EventEmitter<any> = new EventEmitter();

  categorisazo: any;
  categorias: Categoria[];

  compuestazo:any;
  compuestos: Compuesto[];

  constructor(
    private productService: ProductService,
    private formBuilder: FormBuilder,
    private router: Router,
    private categoriaService:CategoriaService,
    private compuestoService: CompuestoService
  ) { }

  getAllCategorias(){
    this.categoriaService.getAllCategorias().subscribe((data)=>{
      this.categorisazo=data;
      this.categorias=this.categorisazo.body;
      console.log(this.categorias);
      
    })
  }

  getAllCompuestos(){
    this.compuestoService.getAllCompuestos().subscribe((data)=>{
      console.log(data);
      
      
      this.compuestazo=data;
      this.compuestos=this.compuestazo.body;
      console.log(this.compuestos);
      
    });
  }

  ngOnInit(): void {
    this.getAllCategorias();
    this.getAllCompuestos();
    this.form=this.formBuilder.group({
      nombreP:[
        this.product.nombreP,
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(70),
        ],
      ],
      descripcionP:[
        this.product.descripcionP,
        [
          Validators.maxLength(1000),
        ],
      ],
      precioP:[
        this.product.precioP,
        [
          Validators.required,
        ],
      ],
      stockP:[
        this.product.stockP,
        [
          Validators.required,
        ],
      ],
      compuesto:[
        this.product.compuesto,
        [
          Validators.required,
        ]
      ],
      categoria:[
        this.product.categoria,
        [
          Validators.required,
        ],
      ],
      imagenP:[
        this.product.imagenP,
      ],
    })
  }

  save(){
    this.onSubmit.emit(this.form.value);
  }

}
