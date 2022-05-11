package com.bionaturista.inicioSesion.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;


@AllArgsConstructor
@Getter
@Setter
public class JwtDto {
    private String token;
    private final String bearer = "Bearer";
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;
}
