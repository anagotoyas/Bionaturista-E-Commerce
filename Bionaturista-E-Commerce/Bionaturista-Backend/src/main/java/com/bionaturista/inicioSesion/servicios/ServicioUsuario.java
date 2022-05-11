package com.bionaturista.inicioSesion.servicios;

import com.bionaturista.inicioSesion.repositorios.RepositorioUsuario;
import com.bionaturista.inicioSesion.entidades.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ServicioUsuario  {
    private final RepositorioUsuario repositorioUsuario;

    public Optional<Usuario> getByNombre(String nombreUsuario){
        return this.repositorioUsuario.findByNombreUsuario(nombreUsuario);
    }

    public boolean existeNombreUsuario(String nombreUsuario){
        return this.repositorioUsuario.existsByNombreUsuario(nombreUsuario);
    }

    public Usuario createUsuario(Usuario usuario){
        return this.repositorioUsuario.save(usuario);
    }


    public Usuario getById(int x){
        return this.repositorioUsuario.findById(x).orElse(new Usuario());
    }

    public void removeUsuarioById(Integer id){
        this.repositorioUsuario.deleteById(id);
    }

    public List<Usuario> listarUsarios(){
        return this.repositorioUsuario.findAll();
    }

}
