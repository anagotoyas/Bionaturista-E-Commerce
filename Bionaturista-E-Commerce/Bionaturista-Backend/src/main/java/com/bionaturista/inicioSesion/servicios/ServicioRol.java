package com.bionaturista.inicioSesion.servicios;

import com.bionaturista.inicioSesion.entidades.Rol;
import com.bionaturista.inicioSesion.enums.RolNombre;
import com.bionaturista.inicioSesion.repositorios.RepositorioRol;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ServicioRol {

    private final RepositorioRol repositorioRol;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return this.repositorioRol.findByRolNombre(rolNombre);
    }
    public Rol createRol(Rol rol){
        return this.repositorioRol.save(rol);
    }
}
