/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Cliente;
import ProyectoFinal.Final.enumeraciones.Rol;
import ProyectoFinal.Final.excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.ClienteRepositorio;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio cliRepo;

    @Autowired
    private ProveedorService proServ;

    @Transactional
    public void registrarCliente(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion) throws miException {
        validar(nombre, apellido, dni, correo, telefono, password, direccion);

        Cliente clie = cliRepo.buscarClientePorDni(dni);
        if (clie != null) {
            throw new miException("El dni ingresado ya está registrado.");
        }

        Cliente cli = new Cliente();
        cli.setNombre(nombre);
        cli.setApellido(apellido);
        cli.setDni(dni);
        cli.setCorreo(correo);
        cli.setTelefono(telefono);
        cli.setPassword(new BCryptPasswordEncoder().encode(password));
        cli.setDireccion(direccion);
        cli.setRol(Rol.USER);

        cliRepo.save(cli);

    }

    //NECESITA MODIFICAR MAS QUE SOLO ESTOS 2 ATRIBUTOS??
    @Transactional
    public void modificarCliente(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion, String id) throws miException {

        validar(nombre, apellido, dni, correo, telefono, password, direccion);

        Optional<Cliente> respuesta = cliRepo.findById(id);

        if (respuesta.isPresent()) {
            Cliente cl = respuesta.get();
            cl.setNombre(nombre);
            cl.setApellido(apellido);
            cl.setCorreo(correo);
            cl.setTelefono(telefono);
            cl.setPassword(new BCryptPasswordEncoder().encode(password));
            cl.setDireccion(direccion);
            cl.setDni(dni);
            cl.setRol(Rol.USER);
            cliRepo.save(cl);
        }
    }

    @Transactional
    public void cambiarRol(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("La identificacion del usuario no es correcta.");
        }

        Optional<Cliente> respuesta = cliRepo.findById(id);

        if (respuesta.isPresent()) {
            Cliente user = respuesta.get();

            if (user.getRol().equals(Rol.USER)) {
                user.setRol(Rol.PROVEEDOR);
            } else {
                user.setRol(Rol.USER);
            }
        }
    }

    @Transactional
    public void registrarCambiado(Cliente cli) {
        if (cli != null) {
            cliRepo.save(cli);
        }
    }

    public List<Cliente> listarClientes() {

        return cliRepo.findAll();

    }

    public Cliente getOne(String id) {
        return cliRepo.getOne(id);
    }

    public Cliente buscarClientePorID(String id) {
        Optional<Cliente> respuesta = cliRepo.findById(id);
        Cliente cli = new Cliente();
        if (respuesta.isPresent()) {
            cli = respuesta.get();
        }
        return cli;
    }

    @Transactional
    public void eliminarCliente(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        cliRepo.deleteById(id);

    }

    @Transactional
    public void darBaja(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("La identificacion del Proveedor no es correcta.");
        }

        Optional<Cliente> respuesta = cliRepo.findById(id);

        if (respuesta.isPresent()) {
            Cliente user = respuesta.get();

            user.setRol(Rol.BAJA);
        }
    }

    public Cliente buscarClientePorDNI(Long DNI) {
        return cliRepo.buscarClientePorDni(DNI);
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