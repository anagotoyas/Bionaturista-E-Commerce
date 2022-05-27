package com.bionaturista.services.impl;

import com.bionaturista.model.EstadoPedido;
import com.bionaturista.model.Pedido;
import com.bionaturista.model.Producto;
import com.bionaturista.model.Usuario;
import com.bionaturista.repositories.EstadoPedidoRepository;
import com.bionaturista.repositories.PedidoRepository;
import com.bionaturista.repositories.ProductoRepository;
import com.bionaturista.repositories.UsuarioRepository;
import com.bionaturista.services.PedidoService;
import com.bionaturista.validators.PedidoValidator;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository){
        this.pedidoRepository=pedidoRepository;
    }

    @Override
    public Pedido pagarPedido(Pedido pedido) {
        //Asigno el montoPago el costoEnvío para de ahí sumarle los productos jeje
        pedido.setMontoPago(pedido.getCostoEnvio());
        //Obtengo el Usuario mediante su idUsuario para obtener su carrito pex
        Integer idsazo=pedido.getUsuario().getIdUsuario();
        Usuario usuarioFinal=usuarioRepository.getById(idsazo);
        //Le sumamos al montoPago los precios de cada product y lo guardamoss
        pedido.setMontoPago(obtenerMontoTotal(usuarioFinal, pedido));
        //Obtiene el subTotal
        pedido.setSubtotal(obtenerSubtotal(pedido));
        //Valida ☼
        PedidoValidator.validate(pedido);
        //Vasea el carrito y se lo guarda al usuario (actualiza *en esencia*) ☼☼☼
        Set<Producto> nuevoCarrito = null;
        usuarioFinal.setCarritoCompras(nuevoCarrito);
        usuarioRepository.save(usuarioFinal);
        //Y guarda el pedido as it should :)
        return pedidoRepository.save(pedido);
    }

    @Override
    public float obtenerMontoTotal(Usuario usuario, Pedido pedido) {
        Set<Producto> productos = usuario.getCarritoCompras();
        float total=pedido.getMontoPago();
        for (Producto producto : productos){
            //Suma de precios
            total+=producto.getPrecioP();
            //Resta al stock de cada productazo
            producto.setStockP(producto.getStockP()-1);
            productoRepository.save(producto);
        }
        return total;
    }

    @Override
    public float obtenerSubtotal(Pedido pedido) {
        float subtotal=0f;
        float montoCompleto=pedido.getMontoPago();
        float montoEnvio= pedido.getCostoEnvio();
        subtotal=montoCompleto-montoEnvio;
        return subtotal;
    }

    @Override
    public Pedido modificarPedido(Pedido pedido) {
        PedidoValidator.validate(pedido);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Integer idPedido) {
        pedidoRepository.deleteById(idPedido);
    }

    @Override
    public Pedido obtenerPedidoPorIdPedido(Integer idPedido) {
        return pedidoRepository.findById(idPedido).orElse(new Pedido());
    }

    @Override
    public List<Pedido> listarPedido() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido modificarEstadoPedido(Integer idPedido) {
        return null;
    }

    @Override
    public void cancelarPedido(Integer idPedido) {
        Pedido pedido=pedidoRepository.findById(idPedido).orElse(new Pedido());
        EstadoPedido enviado=estadoPedidoRepository.pedidoEnviado();
        pedido.setEstadoPedido(enviado);
    }

}
