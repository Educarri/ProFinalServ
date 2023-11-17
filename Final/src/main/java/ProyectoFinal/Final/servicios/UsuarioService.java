
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Usuario;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public void actualizarUsuario(String id, String nombre, String apellido, Long dni, String correo, int telefono, String direccion, String password) throws miException  {
        Usuario usuario = usuarioRepo.getOne(id);
    if (usuario != null) {
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setCorreo(correo);
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setPassword(password);
        
        // Guarda los cambios en el repositorio
        usuarioRepo.save(usuario);
    } else {
        throw new miException("Usuario no encontrado");
    }

    }

}