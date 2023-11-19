
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Usuario;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;
    

    public Usuario getOne(String id) {
        return usuarioRepo.getOne(id);
    }

    public List<Usuario> listarUsuarios() {

        return usuarioRepo.findAll();

    }

    @Transactional
    public void eliminar(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        usuarioRepo.deleteById(id);

    }
    
    
    
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepo.buscarUsuarioPorEmail(correo);

        if (user != null) {
            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession sesion = attr.getRequest().getSession(true);

            sesion.setAttribute("usuarioSesion", user);

            return new User(user.getCorreo(), user.getPassword(), permisos);

        } else {

            return null;
        }

    }

    //pensar la logica para cuando tambien se pueda modificar un proveedor, ya que tendra mas atributos
    // crear logica con condicionales (if) dependiendo el rol que lo mande al controlador -> servicio correspondiente ??
    
    public void actualizarUsuario(String id, String nombre, String apellido, Long dni, String correo, Integer telefono, String direccion, String password) throws miException  {
        Usuario usuario = usuarioRepo.getOne(id);
    if (usuario != null) {
        validar(nombre, apellido, dni, correo, telefono, direccion, password);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        
        // Guarda los cambios en el repositorio
        usuarioRepo.save(usuario);
    } else {
        throw new miException("Usuario no encontrado");
    }

    }
    
    
    public void validar(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion) throws miException {
        if (nombre.isEmpty() || nombre == null) {
            throw new miException("El nombre no puede estar vacio.");
        }

        if (apellido.isEmpty() || apellido == null) {
            throw new miException("El apellido no puede estar vacio.");
        }

        if (dni > 99999999) {
            throw new miException("El dni supera la cantidad de digitos maximos.");
        }

        if (dni == null) {
            throw new miException("El dni no puede estar vacio.");
        }

        if (correo.isEmpty() || correo == null) {
            throw new miException("El correo no puede estar vacio.");
        }

        if (!correo.contains("@")) {
            throw new miException("El correo no contiene el simbolo '@'");
        }

        if (telefono < 0) {
            throw new miException("El numero ingresado es incorrecto.");
        }

        if (telefono == null) {
            throw new miException("El numero de telefono no puede estar vacio.");
        }

        if (password.isEmpty() || password == null) {
            throw new miException("La password no puede estar incompleta.");
        }

        if (!isPasswordValid(password)) {
            throw new miException("La password no cumple con los requisitos de ser Alfanumerica y longitud minimo 6 caracteres");
        }

    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }

}