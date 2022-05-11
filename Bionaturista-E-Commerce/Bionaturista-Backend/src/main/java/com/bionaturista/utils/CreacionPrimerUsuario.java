package com.bionaturista.utils;
import com.bionaturista.inicioSesion.entidades.Rol;
import com.bionaturista.inicioSesion.entidades.Usuario;
import com.bionaturista.inicioSesion.enums.RolNombre;
import com.bionaturista.inicioSesion.servicios.ServicioRol;
import com.bionaturista.inicioSesion.servicios.ServicioUsuario;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Component
public class CreacionPrimerUsuario implements CommandLineRunner {
    private final ServicioRol servicioRol;
    private PasswordEncoder passwordEncoder;
    private final ServicioUsuario servicioUsuario;
    private final String NOMBRE_USUARIO="admin";
    private final String CONTRA_USUARIO="admin";


    @Override
    public void run(String... args) throws Exception {
        try {
            //----------CREACIÓN DE ROLES---------------------
            Rol rolAdmin = new Rol();
            rolAdmin.setRolNombre(RolNombre.ROLE_ADMIN);
            Rol rolUser = new Rol();
            rolUser.setRolNombre(RolNombre.ROLE_USER);
            servicioRol.createRol(rolAdmin);
            servicioRol.createRol(rolUser);
            //----------CREACIÓN DEL USUARIO--------------
            Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setNombreUsuario(NOMBRE_USUARIO);
            usuarioNuevo.setNombreCompleto("José Javier Valencia Barriga");
            usuarioNuevo.setCorreo("JValenciaB@upao.edu.pe");
            usuarioNuevo.setTelefono("+51 950066555");
            usuarioNuevo.setDireccion("No sé");
            usuarioNuevo.setDni("70708080");
            usuarioNuevo.setContrasegnaUsuario(
                    passwordEncoder.encode(CONTRA_USUARIO)
            );
            servicioUsuario.createUsuario(usuarioNuevo);
            //----------OBTENIENDO ROLES Y EL USUARIO CREADO----
            Rol rolAdmin1 = servicioRol.getByRolNombre(RolNombre.ROLE_ADMIN).get();
            Rol rolUser2 = servicioRol.getByRolNombre(RolNombre.ROLE_USER).get();
            Usuario u =servicioUsuario.getByNombre(NOMBRE_USUARIO).get();
            //----------GUARDANDO EL USUARIO CON LOS ROLES OBTENIDOS-------
            Set<Rol> roles = new HashSet<>();
            roles.add(rolUser2);
            roles.add(rolAdmin1);
            u.setRoles(roles);
            servicioUsuario.createUsuario(u);
        }catch (Exception e){
            System.out.println("Roles creados, Error:"+ e);
        }
    }
}
