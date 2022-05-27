package com.bionaturista.repositories;

import com.bionaturista.model.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
    @Query("SELECT e FROM EstadoPedido e WHERE e.nombreEstado = 'Pedido Cancelado'")
    EstadoPedido pedidoCancelado();

    @Query("SELECT e FROM EstadoPedido e WHERE e.nombreEstado = 'Pendiente de Envío'")
    EstadoPedido pedidoEnviado();

}
