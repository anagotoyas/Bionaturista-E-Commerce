package com.bionaturista.controller;

import com.bionaturista.model.Compuesto;
import com.bionaturista.services.CompuestoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/compuestos")
public class CompuestoController {

    private final CompuestoService compuestoService;

    public CompuestoController(CompuestoService compuestoService){
        this.compuestoService=compuestoService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Compuesto>> crearCompuesto(@Valid @RequestBody Compuesto compuesto){
        Compuesto compuestoNew= compuestoService.crearCompuesto(compuesto);
        return new WrapperResponse<>(true, "success", compuestoNew).createResponse();
    }
    @PutMapping
    public ResponseEntity<WrapperResponse<Compuesto>> modificarCompuesto(@Valid @RequestBody Compuesto compuesto){
        Compuesto compuestoUpdate = compuestoService.modificarCompuesto(compuesto);
        return new WrapperResponse<>(true, "success", compuestoUpdate).createResponse();
    }
    @DeleteMapping("/{idCompuesto}")
    public ResponseEntity<WrapperResponse<Void>> eliminarCompuesto(@PathVariable("idCompuesto") Integer idCompuesto){
        compuestoService.eliminarCompuesto(idCompuesto);
        return new WrapperResponse<Void>(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<WrapperResponse<List<Compuesto>>> listarCompuesto(){
        List<Compuesto> compuestos=compuestoService.listarCompuesto();
        return new WrapperResponse<>(true, "success", compuestos).createResponse();
    }
    @GetMapping("/{idCompuesto}")
    public ResponseEntity<WrapperResponse<Compuesto>> obtenerCompuestoPorIdCompuesto(@PathVariable("idCompuesto") Integer idCompuesto){
        Compuesto compuesto=compuestoService.obtenerCompuestoPorIdCompuesto(idCompuesto);
        return new WrapperResponse<>(true, "success", compuesto).createResponse();
    }
}
