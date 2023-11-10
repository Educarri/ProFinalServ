/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.entidades;

<<<<<<< HEAD:Final/src/main/java/servicios/AdminService.java
import entidades.Administrador;
import excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.AdminRepositorio;
=======
import javax.persistence.Entity;
>>>>>>> ccf5e717874618087f7981d6918a61ea05a25247:Final/src/main/java/ProyectoFinal/Final/entidades/Administrador.java


@Entity
public class Administrador extends Usuario{

<<<<<<< HEAD:Final/src/main/java/servicios/AdminService.java
@Service
public class AdminService {
 
    @Autowired
    private AdminRepositorio adminRepo;
=======
    public Administrador() {
    }
>>>>>>> ccf5e717874618087f7981d6918a61ea05a25247:Final/src/main/java/ProyectoFinal/Final/entidades/Administrador.java
    
    @Transactional
    public void registrarAdmin(String nombre, String apellido,
            Long dni, String correo, Integer telefono, String password,
            String direccion) throws miException {
        validar(nombre, apellido, dni, correo, telefono, password, direccion);

        Administrador cli = new Administrador();

        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setCorreo(correo);
        cli.setPassword(password);
        cli.setDireccion(direccion);
        cli.setDNI(dni);
        cli.setTelefono(telefono);

        adminRepo.save(cli);

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

    public void validar(String nombre, String apellido, Long dni, String correo, Integer telefono, String password, String direccion) throws miException {
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

        if (correo.isEmpty() || nombre == null) {
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
