import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from 'src/app/user/products/shared/product.model';
import { ProductService } from '../shared/product.service';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit {

  constructor(public productService:ProductService, private router: Router) { }

  ngOnInit(): void {
  }

  productFinal:Product;

  setImagen(product:any){
    if (product.categoria.nombreCategoria=="Bebidas"){
      return "https://i.imgur.com/A9PluM6.png";
    }
    else if (product.categoria.nombreCategoria=="Batidos"){
      return "https://i.imgur.com/ZhxlnlU.png";
    }
    else if (product.categoria.nombreCategoria=="Cápsulas"){
      return "https://i.imgur.com/klfPTRD.png";
    }
    else if (product.categoria.nombreCategoria=="Polvos"){
      return "https://i.imgur.com/4b2CDKI.png";
    }
    else if (product.categoria.nombreCategoria=="Aceites"){
      return "https://i.imgur.com/ast35Md.png";
    }
    else if (product.categoria.nombreCategoria=="Productos Naturales"){
      return "https://i.imgur.com/5kM2de3.png";
    }
    else if (product.categoria.nombreCategoria=="Uso Capilar"){
      return "https://i.imgur.com/ZVJ3jE6.png";
    }
    else if (product.categoria.nombreCategoria=="Uso Corporal"){
      return "https://i.imgur.com/vPy80NW.png";
    }
    else{
      return "https://i.imgur.com/nKfDcjC.png";
    }
  }


  createProduct(product:Product){
    product.imagenP=this.setImagen(product);
    this.productFinal=product;
    console.log(this.productFinal);
    this.productService.create(this.productFinal).subscribe(
      ()=>{
        this.router.navigate(['admin/productos']);
      },
      (error:any) => {}
    )
  }

}
