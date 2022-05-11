package com.bionaturista.inicioSesion.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPrincipal implements UserDetails {

    private String nombreUsuarioPrincipal;
    private Collection<? extends GrantedAuthority> authorities = new HashSet<>();
    private String contrasegnaUsuarioPrincipal;

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities = usuario.getRoles().stream().map(
                rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())
        ).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombreUsuario(), authorities, usuario.getContrasegnaUsuario());
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasegnaUsuarioPrincipal;
    }

    @Override
    public String getUsername() {
        return nombreUsuarioPrincipal;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
