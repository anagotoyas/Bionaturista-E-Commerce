package com.bionaturista.controller;

import com.bionaturista.model.Categoria;
import com.bionaturista.model.Producto;
import com.bionaturista.services.ProductoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<WrapperResponse<List<Producto>>> listarProducto(){
        List<Producto> productos=productoService.listarProducto();
        return new WrapperResponse<>(true, "success", productos).createResponse();
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<WrapperResponse<Producto>> obtenerProductoPorIdProducto(@PathVariable("idProducto") Integer idProducto){
        Producto producto=productoService.obtenerProductoPorIdProducto(idProducto);
        return new WrapperResponse<>(true, "success", producto).createResponse();
    }
    @GetMapping("/contar")
    public ResponseEntity<WrapperResponse<Long>> contarProductos(){
        Long producto = productoService.countProductos();
        return new WrapperResponse<>(true, "success", producto).createResponse();
    }
}
