import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { HeaderComponent } from './header/header.component';
import { ListProductsComponent } from './list-products/list-products.component';
import { NewProductComponent } from './products/new-product/new-product.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { VentasComponent } from './ventas/ventas.component';

const routes: Routes = [

  {
    
    path:'',
    component: AdminHomeComponent,
    children: [
      {
        path: '',
        component: ListProductsComponent,
      },
      {
        path: 'ventas',
        component: VentasComponent,
      },
      {
        path: 'productos',
        component: ListProductsComponent
      },
      {
        path: 'productos/crear',
        component: NewProductComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
