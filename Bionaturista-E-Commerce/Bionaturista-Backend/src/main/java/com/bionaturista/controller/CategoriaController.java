package com.bionaturista.controller;


import com.bionaturista.model.Categoria;
import com.bionaturista.services.CategoriaService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService=categoriaService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Categoria>> crearCategoria(@Valid @RequestBody Categoria categoria){
        Categoria categoriaNew= categoriaService.crearCategoria(categoria);
        return new WrapperResponse<>(true, "success", categoriaNew).createResponse();
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<Categoria>> modificarCategoria(@Valid @RequestBody Categoria categoria){
        Categoria categoriaUpdate = categoriaService.modificarCategoria(categoria);
        return new WrapperResponse<>(true, "success", categoriaUpdate).createResponse();
    }
    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<WrapperResponse<Void>> eliminarCategoria(@PathVariable("idCategoria") Integer idCategoria){
        categoriaService.eliminarCategoria(idCategoria);
        return new WrapperResponse<Void>(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<WrapperResponse<List<Categoria>>> listarCategoria(){
        List<Categoria> categorias=categoriaService.listarCategoria();
        return new WrapperResponse<>(true, "success", categorias).createResponse();
    }
    @GetMapping("/{idCategoria}")
    public ResponseEntity<WrapperResponse<Categoria>> obtenerCategoriaPorIdCategoria(@PathVariable("idCategoria") Integer idCategoria){
        Categoria categoria=categoriaService.obtenerCategoriaPorIdCategoria(idCategoria);
        return new WrapperResponse<>(true, "success", categoria).createResponse();
    }
}
