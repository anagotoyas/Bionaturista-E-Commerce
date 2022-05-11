package com.bionaturista.inicioSesion.controladores;


import com.bionaturista.inicioSesion.dto.JwtDto;
import com.bionaturista.inicioSesion.dto.LoginUsuario;
import com.bionaturista.inicioSesion.dto.NuevoUsuario;
import com.bionaturista.inicioSesion.entidades.Rol;
import com.bionaturista.inicioSesion.entidades.Usuario;
import com.bionaturista.inicioSesion.enums.RolNombre;
import com.bionaturista.inicioSesion.jwt.JwtProvider;
import com.bionaturista.inicioSesion.servicios.ServicioRol;
import com.bionaturista.inicioSesion.servicios.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@AllArgsConstructor
public class ControladorInicioSesion {

    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private ServicioUsuario servicioUsuario;
    private ServicioRol servicioRol;


    @PostMapping("/login")
    public ResponseEntity<JwtDto> logginUsuario(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bin){
        if(bin.hasErrors()){
            return new ResponseEntity("Error en alguno de los campos", HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUsuario.getNombreUsuario(),
                        loginUsuario.getContrasegnaUsuario()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }


    @PostMapping("/new")
    public ResponseEntity<?> nuevoUsuario(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bin) {
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
        usuarioNuevo.setRoles(roles);
        servicioUsuario.createUsuario(usuarioNuevo);

        return new ResponseEntity<Usuario>(usuarioNuevo, HttpStatus.CREATED);
    }



}
