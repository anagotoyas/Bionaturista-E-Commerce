import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RegistrarUsuarioComponent } from './registrar/registrar-usuario/registrar-usuario.component';
import { FormRegistrarUsuarioComponent } from './registrar/form-registrar-usuario/form-registrar-usuario.component';
import { RouterModule } from '@angular/router';
import { CrearCompuestoComponent } from './admin/crear-compuesto/crear-compuesto.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrarUsuarioComponent,
    FormRegistrarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [CrearCompuestoComponent]
})
export class AppModule { }
