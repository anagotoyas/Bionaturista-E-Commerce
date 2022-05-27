package com.bionaturista.controller;

import com.bionaturista.model.EstadoPedido;
import com.bionaturista.services.EstadoPedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoPedidoController {
    private final EstadoPedidoService estadoPedidoService;

    public EstadoPedidoController(EstadoPedidoService estadoPedidoService){
        this.estadoPedidoService=estadoPedidoService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<EstadoPedido>> crearEstado(@Valid @RequestBody EstadoPedido estadoPedido){
        EstadoPedido estadoPedidoNew= estadoPedidoService.crearEstado(estadoPedido);
        return new WrapperResponse<>(true, "success",estadoPedidoNew).createResponse();
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<EstadoPedido>> modificarEstado(@Valid @RequestBody EstadoPedido estadoPedido){
        EstadoPedido estadoPedidoUpdate= estadoPedidoService.modificarEstado(estadoPedido);
        return new WrapperResponse<>(true, "success",estadoPedidoUpdate).createResponse();
    }

    @DeleteMapping("/{idEstado}")
    public ResponseEntity<WrapperResponse<Void>> eliminarEstado(@PathVariable("idEstado") Integer idEstado){
        estadoPedidoService.eliminarEstado(idEstado);
        return new WrapperResponse<Void>(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
    }

    @GetMapping()
    public ResponseEntity<WrapperResponse<List<EstadoPedido>>> listarEstado(){
        List<EstadoPedido> estados = estadoPedidoService.listarEstado();
        return new WrapperResponse<>(true, "success", estados).createResponse();
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<WrapperResponse<EstadoPedido>> obtenerEstadoPorIdEstado(@PathVariable("idEstado") Integer idEstado) {
        EstadoPedido estadoPedido = estadoPedidoService.obtenerEstadoPorIdEstado(idEstado);
        return new WrapperResponse<>(true,"success", estadoPedido).createResponse();
    }


}
