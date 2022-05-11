package com.bionaturista.inicioSesion.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class NuevoUsuario {

    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String contrasegnaUsuario;
    @NotBlank
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    @NotBlank
    private String dni;
    @NotBlank
    private String correo;
    private Set<String> roles = new HashSet<>();

}
