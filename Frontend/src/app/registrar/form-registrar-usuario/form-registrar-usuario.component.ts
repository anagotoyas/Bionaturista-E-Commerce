import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { Usuario } from 'src/app/shared/usuario.model';
import { UsuarioService } from 'src/app/shared/usuario.service';

@Component({
  selector: 'app-form-registrar-usuario',
  templateUrl: './form-registrar-usuario.component.html',
  styleUrls: ['./form-registrar-usuario.component.css']
})
export class FormRegistrarUsuarioComponent implements OnInit {
  usuario=new Usuario();
  msg='';
  form:FormGroup;
  @Output() onSubmit: EventEmitter<any>=new EventEmitter();
  constructor(
    public formBuilder:FormBuilder,
    public register: UsuarioService,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.form=this.formBuilder.group({
      nombreUsuario:[
        this.usuario.nombreUsuario,
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(100),
        ],
      ],
      correoUsuario:[
        this.usuario.correoUsuario,
        [
          Validators.required,
          Validators.email
        ],
      ],
      contrasenaUsuario:[
        this.usuario.contrasenaUsuario,
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(25),
        ],
      ],
      direccionUsuario:[
        this.usuario.direccionUsuario=''
      ],
      rol:[
        this.usuario.rol = {idRol:2}
      ],
    })
  }
  
  registrar(){
    this.msg=""
    if(this.form.value.nombreUsuario==null || this.form.value.correoUsuario==null || this.form.value.contrasenaUsuario==null){
      this.msg="Debe llenar todos los campos."
    }
    else if(this.form.value.nombreUsuario.lenght<1 || this.form.value.contrasenaUsuario.lenght<1 || this.form.value.correoUsuario.lenght<1){
      this.msg="Llene correctamente los campos."
    }
    this.onSubmit.emit(this.form.value);
    console.log(this.msg);
    console.log(this.form.value.nombreUsuario);
    
    
  }
}
