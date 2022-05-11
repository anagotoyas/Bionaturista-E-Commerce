package com.bionaturista.validators;

import com.bionaturista.exception.IncorrectResourceRequestException;
import com.bionaturista.model.Categoria;

public class CategoriaValidator {

    public static void validate(Categoria categoria){
        if (categoria.getNombreCategoria()==null || categoria.getNombreCategoria().trim().isEmpty()){
            throw new IncorrectResourceRequestException("El nombre de la categoria no puede estar vacío.");
        }
        if(categoria.getNombreCategoria().length() < 5){
            throw new IncorrectResourceRequestException("El nombre de la categoria debe ser mayor a 5 caracteres.");
        }
        if(categoria.getNombreCategoria().length() > 50){
            throw new IncorrectResourceRequestException("El nombre de la categoria debe ser menor a 50 caracteres.");
        }
    }
}
