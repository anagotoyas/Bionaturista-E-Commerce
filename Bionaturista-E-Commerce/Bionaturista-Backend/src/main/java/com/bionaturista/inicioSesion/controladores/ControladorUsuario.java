package com.bionaturista.inicioSesion.controladores;


import com.bionaturista.inicioSesion.dto.NuevoUsuario;
import com.bionaturista.inicioSesion.entidades.Rol;
import com.bionaturista.inicioSesion.entidades.Usuario;
import com.bionaturista.inicioSesion.enums.RolNombre;
import com.bionaturista.inicioSesion.servicios.ServicioRol;
import com.bionaturista.inicioSesion.servicios.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
@AllArgsConstructor
public class ControladorUsuario {

    private PasswordEncoder passwordEncoder;
    private ServicioUsuario servicioUsuario;
    private ServicioRol servicioRol;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bin) {
        if (bin.hasErrors()) {
            return new ResponseEntity<String>("Error en alguno de los campos", HttpStatus.BAD_REQUEST);
        } else if(servicioUsuario.existeNombreUsuario(nuevoUsuario.getNombreUsuario())){
            return new ResponseEntity<String>("Ya existe el nombre", HttpStatus.BAD_REQUEST);
        }
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombreUsuario(nuevoUsuario.getNombreUsuario());
        usuarioNuevo.setNombreCompleto(nuevoUsuario.getNombreCompleto());
        usuarioNuevo.setCorreo(nuevoUsuario.getCorreo());
        usuarioNuevo.setTelefono(nuevoUsuario.getTelefono());
        usuarioNuevo.setDireccion(nuevoUsuario.getDireccion());
        usuarioNuevo.setDni(nuevoUsuario.getDni());
        usuarioNuevo.setContrasegnaUsuario(
                passwordEncoder.encode(nuevoUsuario.getContrasegnaUsuario())
        );
        Set<Rol> roles = new HashSet<>();
        roles.add(servicioRol.getByRolNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles().contains("admin")){
            roles.add(servicioRol.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuarioNuevo.setRoles(roles);
        servicioUsuario.createUsuario(usuarioNuevo);
        return new ResponseEntity<Usuario>(usuarioNuevo, HttpStatus.CREATED);

    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("idUsuario") Integer idUsuario){
        this.servicioUsuario.removeUsuarioById(idUsuario);
        return new ResponseEntity<String>("Usuario Borrado", HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return new ResponseEntity<List<Usuario>>(
                this.servicioUsuario.listarUsarios(),
                HttpStatus.OK
        );
    }

}
