package com.bionaturista.controller;

import com.bionaturista.model.Pedido;
import com.bionaturista.model.Usuario;
import com.bionaturista.repositories.UsuarioRepository;
import com.bionaturista.services.PedidoService;
import com.bionaturista.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<WrapperResponse<Pedido>> pagarPedido(@Valid @RequestBody Pedido pedido) throws Exception{
        Pedido pedidoNull=null;
        Integer idUsuario=pedido.getUsuario().getIdUsuario();
        Usuario usuario=usuarioRepository.getById(idUsuario);
        if(usuario.getCarritoCompras().isEmpty()){
            return new WrapperResponse<>(false, "El carro de compras está vacío crack.", pedidoNull).createResponse();
        }
        else {
            Pedido pedidoNew = pedidoService.pagarPedido(pedido);
            return new WrapperResponse<>(true, "success", pedidoNew).createResponse();
        }
    }

    @PutMapping
    public ResponseEntity<WrapperResponse<Pedido>> modificarPedido(@Valid @RequestBody Pedido pedido){
        Pedido pedidoUpdate=pedidoService.modificarPedido(pedido);
        return new WrapperResponse<>(true, "success",pedido).createResponse();
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<WrapperResponse<Void>> eliminarPedido(@PathVariable("idPedido") Integer idPedido){
        pedidoService.eliminarPedido(idPedido);
        return new WrapperResponse<Void>(true, "success", null).createResponse(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<WrapperResponse<List<Pedido>>> listarPedido() {
        List<Pedido> pedidos = pedidoService.listarPedido();
        return new WrapperResponse<>(true, "success", pedidos).createResponse();
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<WrapperResponse<Pedido>> obtenerPedidoPorIdPedido(@PathVariable("idPedido") Integer idPedido) {
        Pedido pedido = pedidoService.obtenerPedidoPorIdPedido(idPedido);
        return new WrapperResponse<>(true, "success", pedido).createResponse();
    }
}