package com.bionaturista.inicioSesion.jwt;


//Genera en cada petición, valida con los otros jwt, permite el acceso


import com.bionaturista.inicioSesion.servicios.ImplUserDetailService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtTokenFilter.class);
    private final ImplUserDetailService userDetailService;
    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = this.getToken(request);
            if (token!=null && this.jwtProvider.validateToken(token)){
                String nombreUsuario = jwtProvider.getNombreUsuarioFromTokem(token);
                UserDetails userDetails = userDetailService.loadUserByUsername(nombreUsuario);
                UsernamePasswordAuthenticationToken passwordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
            }
        }catch (Exception e){
            LOGGER.error("Fallo en doFilterInternal" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request){

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer")){
            return header.replace("Bearer ","");
        }
        return null;
    }
}
