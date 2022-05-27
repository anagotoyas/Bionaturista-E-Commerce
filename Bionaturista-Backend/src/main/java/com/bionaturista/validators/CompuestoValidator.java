package com.bionaturista.validators;

import com.bionaturista.exception.IncorrectResourceRequestException;
import com.bionaturista.model.Compuesto;

public class CompuestoValidator {

    public static void validate(Compuesto compuesto){
        if (compuesto.getNombreCompuesto()==null || compuesto.getNombreCompuesto().trim().isEmpty()){
            throw new IncorrectResourceRequestException("El nombre de compuesto no puede estar vacío.");
        }
        if(compuesto.getNombreCompuesto().length() < 3){
            throw new IncorrectResourceRequestException("El nombre de compuesto debe ser mayor a 2 caracteres.");
        }
        if(compuesto.getNombreCompuesto().length() > 100){
            throw new IncorrectResourceRequestException("El nombre de compuesto debe ser menor a 100 caracteres.");
        }
    }
}
