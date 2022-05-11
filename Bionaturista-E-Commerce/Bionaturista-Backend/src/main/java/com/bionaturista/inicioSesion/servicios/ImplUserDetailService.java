package com.bionaturista.inicioSesion.servicios;

import com.bionaturista.inicioSesion.entidades.Usuario;
import com.bionaturista.inicioSesion.entidades.UsuarioPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
 public class ImplUserDetailService implements UserDetailsService {

    private final ServicioUsuario servicioUsuario;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = servicioUsuario.getByNombre(username).get();

        return UsuarioPrincipal.build(usuario);
    }
}
