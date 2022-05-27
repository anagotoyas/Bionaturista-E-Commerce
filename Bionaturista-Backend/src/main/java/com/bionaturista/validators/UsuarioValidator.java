package com.bionaturista.validators;

import com.bionaturista.exception.IncorrectResourceRequestException;
import com.bionaturista.model.Usuario;

public class UsuarioValidator {

    public static void validate(Usuario usuario) {

        if (usuario.getNombreUsuario() == null || usuario.getNombreUsuario().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("El nombre de usuario no puede estar vacío");
        }

        if(usuario.getNombreUsuario().length() < 3) {
            throw new IncorrectResourceRequestException("El nombre de usuario debe ser mayor a 3 caracteres");

        }

        if(usuario.getNombreUsuario().length() > 100) {
            throw new IncorrectResourceRequestException("El nombre de usuario debe ser menor de 100 caracteres");

        }

        if (usuario.getCorreoUsuario() == null || usuario.getCorreoUsuario().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("El correo del usuario no puede estar vacío");
        }

        if (usuario.getContrasenaUsuario() == null || usuario.getContrasenaUsuario().trim().isEmpty()) {
            throw new IncorrectResourceRequestException("La contraseña no puede estar vacía");
        }

        if(usuario.getContrasenaUsuario().length() < 6) {
            throw new IncorrectResourceRequestException("El tamaño de la contraseña debe ser mayor a 6 caracteres");

        }

        if(usuario.getContrasenaUsuario().length() > 25) {
            throw new IncorrectResourceRequestException("El tamaño de la contraseña debe ser menor de 25 caracteres");

        }

        if(usuario.getDireccionUsuario().length() > 50) {
            throw new IncorrectResourceRequestException("La direccion del usuario debe ser menor de 50 caracteres");

        }

    }

}
