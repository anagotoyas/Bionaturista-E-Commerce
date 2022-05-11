package com.bionaturista.inicioSesion.jwt;


//Genera el token es valido-->formación, expriación

import com.bionaturista.inicioSesion.entidades.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

//    @Value("${jwt.secret}")
    private final String secret = "secretTiendaVera";

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        //    @Value("${jwt.expiration}")
        int expiration = 36000;
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration *1000L))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getNombreUsuarioFromTokem(String token){

        return this.getClaims(token).getSubject();
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            LOGGER.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            LOGGER.error("Token no soportado");
        }catch (ExpiredJwtException e){
            LOGGER.error("Token ha expirado");
        }catch (IllegalArgumentException e){
            LOGGER.error("Token vacio" + e.getMessage());
        }catch (SignatureException e){
            LOGGER.error("fallo con la firma");
        }
        return false;
    }

}
