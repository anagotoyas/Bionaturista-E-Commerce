package com.bionaturista.validators;

import com.bionaturista.exception.IncorrectResourceRequestException;
import com.bionaturista.model.EstadoPedido;

public class EstadoPedidoValidator {
    public static void validate(EstadoPedido estadoPedido){
        if (estadoPedido.getNombreEstado() == null || estadoPedido.getNombreEstado().trim().isEmpty()){
            throw new IncorrectResourceRequestException("El estado no puede estar vacío.");
        }

        if (estadoPedido.getNombreEstado().length() < 3){
            throw new IncorrectResourceRequestException("El estado debe ser mayor a 3 caracteres.");
        }

        if (estadoPedido.getNombreEstado().length() > 50){
            throw new IncorrectResourceRequestException("El estado debe ser menor a 50 caracteres.");
        }
    }
}
