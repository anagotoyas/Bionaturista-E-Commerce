import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/shared/usuario.model';
import { UsuarioService } from 'src/app/shared/usuario.service';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {
  msg=""
  form:FormGroup;
  @Output() onSubmit: EventEmitter<any> = new EventEmitter();
  constructor(
    private formBuilder:FormBuilder,
    public usuario:UsuarioService,
    public router:Router,
  ) { }

  ngOnInit(): void {
  }

  registerUser(usuario: Usuario){
    this.usuario.register(usuario).subscribe(
      (res:any)=>{
        if(res['message']=='success'){
          const {idUsuario} = res['body']
          this.router.navigate(['']);
          console.log(res)
          this.msg=""
          sessionStorage.setItem('idUsuario', idUsuario)
        }
        else{
          this.msg=res['message']
          
          console.log(this.msg);
          console.log(res);        
        }
      },
      (error:any)=>{
        this.msg=""
      }
    )
  }
}
