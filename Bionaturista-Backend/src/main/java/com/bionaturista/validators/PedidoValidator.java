package com.bionaturista.validators;

import com.bionaturista.exception.IncorrectResourceRequestException;
import com.bionaturista.model.Pedido;
import com.bionaturista.model.Usuario;
import com.bionaturista.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PedidoValidator {

    public static void validate(Pedido pedido){

        if (pedido.getEstadoPedido() == null){
            throw new IncorrectResourceRequestException("El estado no puede estar vacío.");
        }
        if (pedido.getMetodoPago() == null || pedido.getMetodoPago().trim().isEmpty()){
            throw new IncorrectResourceRequestException("El método de pago no puede estar vacío.");
        }

        if (pedido.getMetodoPago().length() < 3){
            throw new IncorrectResourceRequestException("El método de pago debe ser mayor a 3 caracteres.");
        }

        if (pedido.getMetodoPago().length() > 50){
            throw new IncorrectResourceRequestException("El método de pago debe ser menor a 50 caracteres.");
        }
    }
}
