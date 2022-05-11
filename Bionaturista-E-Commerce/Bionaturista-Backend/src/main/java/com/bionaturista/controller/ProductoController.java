package com.bionaturista.controller;

import com.bionaturista.model.Producto;
import com.bionaturista.services.ProductoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Producto>> crearProducto(@Valid @RequestBody Producto producto){
        Producto productoNew= productoService.crearProducto(producto);
        return new WrapperResponse<>(true, "success", productoNew).createResponse();
    }
}
