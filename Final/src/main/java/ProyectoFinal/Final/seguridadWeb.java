package ProyectoFinal.Final;

import ProyectoFinal.Final.servicios.UsuarioService;
import ProyectoFinal.Final.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/cliente/lista").hasRole("ADMIN")
                .antMatchers("/trabajo/listaTrabajos").hasRole("ADMIN")
                .antMatchers("/trabajo/*").hasAnyRole("ADMIN", "USER", "PROVEEDOR")
                .antMatchers("/proveedor/lista").hasRole("ADMIN")
                .antMatchers("/imagen/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .antMatchers("/login", "/error", "/", "/registrar/*").permitAll()
                //.anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/logincheck")
                .usernameParameter("correo")
                .passwordParameter("password")
                .defaultSuccessUrl("/inicio") //crearlo para los usuarios con permisos / si todo esta bien se mostrara esta vista
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login") //url a la que se dirije si el logout es exitoso
                .permitAll()
                .and().csrf()
                .disable();
    }
}