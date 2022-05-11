package com.bionaturista.inicioSesion.repositorios;

import com.bionaturista.inicioSesion.entidades.Rol;
import com.bionaturista.inicioSesion.enums.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioRol extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
