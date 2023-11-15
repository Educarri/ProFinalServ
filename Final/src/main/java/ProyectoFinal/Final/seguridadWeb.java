/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal.Final;

import ProyectoFinal.Final.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author andre
 */

    
    @Configuration
@EnableWebSecurity
public class seguridadWeb extends WebSecurityConfigurerAdapter {

    @Autowired
    public UsuarioService usuServ;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuServ)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
                .authorizeHttpRequests()
                    .antMatchers("/admin/*").hasRole("ADMIN") //los unicos usuarios que entren al controlador admin son los que tengan este rol
                    .antMatchers("/cliente/*").hasRole("USER") 
                    .antMatchers("/proveedor/*").hasRole("PROVEEDOR") 
                    .antMatchers("/css/*", "/js/*", "/img/*") .permitAll()
                    .antMatchers("/login", "/registrar", "/registro", "/error").permitAll();
                    //.anyRequest().authenticated()
//                .and().formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/logincheck")
//                    .usernameParameter("correo")
//               .passwordParameter("password")
//                    .defaultSuccessUrl("/inicio") //crearlo para los usuarios con permisos / si todo esta bien se mostrara esta vista
//                    .permitAll()
//                .and().logout()
//                    .logoutUrl("/logout")
//                    .logoutSuccessUrl("/login") //url a la que se dirije si el logout es exitoso
//                    .permitAll()
//                .and().csrf()
//                    .disable();
    }
}

    

