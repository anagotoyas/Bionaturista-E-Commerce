import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrarUsuarioComponent } from './registrar/registrar-usuario/registrar-usuario.component';

const routes: Routes = [
  {
    path:'user',
    loadChildren: () =>
    import('./user/user.module').then((m) => m.UserModule)
  },
  {
    path: '',
    component: LoginComponent
  },

  {
    path:'register',
    component:RegistrarUsuarioComponent
  },
  {
    path:'admin',
    loadChildren: () =>
    import('./admin/admin.module').then((m)=>m.AdminModule)
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
