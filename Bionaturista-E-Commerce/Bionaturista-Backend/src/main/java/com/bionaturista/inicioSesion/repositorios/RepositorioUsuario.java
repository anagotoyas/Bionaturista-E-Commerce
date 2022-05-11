package com.bionaturista.inicioSesion.repositorios;

import com.bionaturista.inicioSesion.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuario  extends JpaRepository<Usuario, Integer>  {

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
}
