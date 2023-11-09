/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import entidades.Cliente;
import excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.ClienteRepositorio;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio cliRepo;

    @Transactional
    public void registrarCliente(String nombre, String apellido,
            Long dni, String correo, Integer telefono, String password,
            String direccion) throws miException {
        validar(nombre, apellido, dni, correo, telefono, password, direccion);

        Cliente cli = new Cliente();

        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setCorreo(correo);
        cli.setPassword(password);
        cli.setDireccion(direccion);
        cli.setDNI(dni);
        cli.setTelefono(telefono);

        cliRepo.save(cli);

    }

    //AGREGAR METODO PARA QUE EL USUARIO PUEDA MODIFICAR SUS DATOS ??
    
    
    public List<Cliente> listarClientes() {

        return cliRepo.findAll();

    }

    public Cliente getOne(String id) {
        return cliRepo.getOne(id);
    }

    @Transactional
    public void eliminarCliente(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        cliRepo.deleteById(id);

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
