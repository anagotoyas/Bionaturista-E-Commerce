import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { ProductListComponent } from './products/product-list/product-list.component';
import { ProductViewComponent } from './products/product-view/product-view.component';
import { MaterialModule } from '../material/material.module';
import { CartViewComponent } from './shoppingCart/cart-view/cart-view.component';
import { ProcesoCompraComponent } from './shoppingCart/proceso-compra/proceso-compra.component';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatTabsModule} from "@angular/material/tabs";
import {MatNativeDateModule} from "@angular/material/core";


@NgModule({
  declarations: [
    LayoutComponent,
    ProductListComponent,
    ProductViewComponent,
    LayoutComponent,
    CartViewComponent,
    ProcesoCompraComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    MaterialModule,
    MatProgressBarModule,
    ReactiveFormsModule,
    MatSelectModule,
    NgxMaterialTimepickerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTabsModule,
    FormsModule
  ]
})
export class UserModule { }
