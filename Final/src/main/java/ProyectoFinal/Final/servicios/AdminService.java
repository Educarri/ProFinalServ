/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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


@Service
public class AdminService {
 
    @Autowired
    private AdminRepositorio adminRepo;
    
    @Transactional
    public Administrador registrarAdmin(Administrador admin) throws miException {
        validar(admin);
        
        admin.setRol(Rol.ADMIN);
        return adminRepo.save(admin);
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

    public void validar(Administrador admin) throws miException {
        if (admin.getNombre().isEmpty() || admin.getNombre() == null) {
            throw new miException("El nombre no puede estar vacio.");
        }

        if (admin.getApellido().isEmpty() || admin.getApellido() == null) {
            throw new miException("El apellido no puede estar vacio.");
        }

        if (admin.getDNI() > 99999999) {
            throw new miException("El dni supera la cantidad de digitos maximos.");
        }

        if (admin.getDNI() == null) {
            throw new miException("El dni no puede estar vacio.");
        }

        if (admin.getCorreo().isEmpty() || admin.getNombre() == null) {
            throw new miException("El correo no puede estar vacio.");
        }

        if (!admin.getCorreo().contains("@")) {
            throw new miException("El correo no contiene el simbolo '@'");
        }

        if (admin.getTelefono() < 0) {
            throw new miException("El numero ingresado es incorrecto.");
        }

        if (admin.getTelefono() == null) {
            throw new miException("El numero de telefono no puede estar vacio.");
        }

        if (admin.getPassword().isEmpty() || admin.getPassword() == null) {
            throw new miException("La password no puede estar incompleta.");
        }

        if (!isPasswordValid(admin.getPassword())) {
            throw new miException("La password no cumple con los requisitos de ser Alfanumerica y longitud minimo 6 caracteres");
        }

    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }

    
    
    
}
