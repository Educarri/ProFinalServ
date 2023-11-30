package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Administrador;
import ProyectoFinal.Final.enumeraciones.Rol;
import ProyectoFinal.Final.excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.AdminRepositorio;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AdminService {

    @Autowired
    private AdminRepositorio adminRepo;

    @Transactional
    public void registrarAdmin(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion) throws miException {
        
        validar(nombre, apellido, dni, correo, telefono, password, direccion);

        Administrador admin = new Administrador();
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setDni(dni);
        admin.setCorreo(correo);
        admin.setTelefono(telefono);
        admin.setPassword(new BCryptPasswordEncoder().encode(password));
        admin.setDireccion(direccion);
        admin.setRol(Rol.ADMIN);
        adminRepo.save(admin);
    }
    
    
    @Transactional
    public void modificarAdmin(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion, String id) throws miException {

        validar(nombre, apellido, dni, correo, telefono, password, direccion);
        
        Optional<Administrador> respuesta = adminRepo.findById(id);

        if (respuesta.isPresent()) {
            Administrador admin = respuesta.get();
            admin.setNombre(nombre);
            admin.setApellido(apellido);
            admin.setCorreo(correo);
            admin.setTelefono(telefono);
            admin.setPassword(new BCryptPasswordEncoder().encode(password));
            admin.setDireccion(direccion);
            admin.setDni(dni);
            admin.setRol(Rol.ADMIN);
            adminRepo.save(admin);
        }
    }

    //AGREGAR METODO PARA QUE EL USUARIO PUEDA MODIFICAR SUS DATOS ??
    public List<Administrador> listarAdministradores() {

        return adminRepo.findAll();

    }

    public Administrador getOne(String id) {
        return adminRepo.getOne(id);
    }

    @Transactional
    public void eliminarAdministrador(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        adminRepo.deleteById(id);

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