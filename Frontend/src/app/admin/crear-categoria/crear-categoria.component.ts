import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {Categoria} from "../shared/categoria.model";
import {CategoriaService} from "../shared/categoria.service";

@Component({
  selector: 'app-crear-categoria',
  templateUrl: './crear-categoria.component.html',
  styleUrls: ['./crear-categoria.component.css']
})
export class CrearCategoriaComponent implements OnInit {
  categoriaForm: FormGroup;

  @Input() categoria: Categoria = new Categoria();
  @Output() onSubmit: EventEmitter<any> = new EventEmitter();


  constructor(public categoriaService: CategoriaService, private formBuilder: FormBuilder,
              private dialogRef: MatDialogRef<CrearCategoriaComponent>,
  ) { }

  ngOnInit(): void {
    this.categoriaForm = this.formBuilder.group({
      nombreCategoria: [
        this.categoria.nombreCategoria,
        [
          Validators.minLength(3),
          Validators.maxLength(100),
          Validators.required
        ]]
    })
  }

  crearCategoria() {
    this.categoriaService.crearCategoria(this.categoriaForm.value).subscribe({
      next: () => {
        this.categoriaForm.reset();
        this.dialogRef.close('save');
      },
      error: ()=> {


      }
    })
  }

}
