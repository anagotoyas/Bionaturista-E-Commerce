package com.bionaturista.services.impl;

import com.bionaturista.model.Producto;
import com.bionaturista.model.Usuario;
import com.bionaturista.repositories.UsuarioRepository;
import com.bionaturista.services.UsuarioService;
import com.bionaturista.validators.UsuarioValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        UsuarioValidator.validate(usuario);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(Integer idUsuario) {

    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorIdUsuario(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(new Usuario());
    }

    @Override
    public void agregarProductoAlCarrito(Usuario usuario, Producto producto) {

        if(producto.getStockP()>0){
            usuario.getCarritoCompras().add(producto);
        }

    }
    @Override
    public void eliminarProductoAlCarrito(Usuario usuario, Producto producto) {

        usuario.getCarritoCompras().remove(producto);


    }

    @Override
    public Usuario fetchUserByCorreoyContra(String correoUsuario, String contrasenaUsuario){
        return usuarioRepository.findUsuarioByCorreoUsuarioAndContrasenaUsuario(correoUsuario, contrasenaUsuario);
    }
}
