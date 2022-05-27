import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../shared/usuario.model';
import { UsuarioService } from '../shared/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  usuario=new Usuario();
  msg='';
  id:any;

  constructor(private service: UsuarioService, private router:Router) { }

  ngOnInit(): void {
  }

  loginUser(){
    if(this.usuario.correoUsuario==null || this.usuario.contrasenaUsuario==null){
      this.msg="Debe llenar todos los campos."
    }
    else{
      this.service.signIn(this.usuario)
      .subscribe(
        res=>{
          console.log(res)
          var idRol= res.body.rol.idRol;
          console.log(idRol);
          this.id=res.body.idUsuario
          sessionStorage.setItem('key',this.id);
          console.log(sessionStorage.getItem('key'))
          
          const{idUsuario} =res
          console.log(idUsuario);
          
          if(idRol==2){
            window.location.replace("../user");
            //this.router.navigate(['/']);
            sessionStorage.setItem('idUsuario',idUsuario)
          }
          else{
            window.location.replace("../admin");
            //this.router.navigate(['/']);
            sessionStorage.setItem('idUsuario',idUsuario)
          }
        },

        err=>{
          console.log(err)
          this.msg="Correo o contraseña incorrectos."
        }

      )
    }
  }
}
