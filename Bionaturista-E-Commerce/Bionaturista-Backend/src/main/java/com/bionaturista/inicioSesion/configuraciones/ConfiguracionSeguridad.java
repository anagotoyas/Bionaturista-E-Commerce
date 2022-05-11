package com.bionaturista.inicioSesion.configuraciones;


import com.bionaturista.inicioSesion.jwt.JwtEntryPoint;
import com.bionaturista.inicioSesion.jwt.JwtProvider;
import com.bionaturista.inicioSesion.jwt.JwtTokenFilter;
import com.bionaturista.inicioSesion.servicios.ImplUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

    private final ImplUserDetailService userDetailService;
    private final JwtEntryPoint jwtEntryPoint;
    //private final JwtTokenFilter jwtTokenFilter;


    @Bean
    public JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter(userDetailService, new JwtProvider());
    }



    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(this.getPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated().and().httpBasic();

        http.cors().and().csrf().disable().authorizeRequests().
                antMatchers("/auth/**").permitAll()
                .and().authorizeRequests().antMatchers("/home/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(this.jwtEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
