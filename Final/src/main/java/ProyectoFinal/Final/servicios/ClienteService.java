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

@Service
public class ClienteService {

    @Autowired
    private ClienteRepositorio cliRepo;

    @Transactional
    public Cliente registrarCliente(Cliente cli) throws miException {
        validar(cli);

        Cliente clie = cliRepo.buscarClientePorDni(cli.getDni());
        if (clie != null) {
            throw new miException("El dni ingresado ya está registrado.");
        }

        cli.setRol(Rol.USER);

        return cliRepo.save(cli);

    }

    //NECESITA MODIFICAR MAS QUE SOLO ESTOS 2 ATRIBUTOS??
    public Cliente modificarCliente(String id, Cliente cli) throws miException {

        validar(cli);
        Cliente cl = cliRepo.getById(id);
        cl.setNombre(cli.getNombre());
        cl.setApellido(cli.getApellido());
        cl.setCorreo(cli.getCorreo());
        cl.setTelefono(cli.getTelefono());
        cl.setPassword(cli.getPassword());
        cl.setDireccion(cli.getDireccion());

        return cliRepo.save(cl);

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

    public Cliente buscarClientePorDNI(Long DNI) {
        return cliRepo.buscarClientePorDni(DNI);
    }

    public void validar(Cliente cli) throws miException {
        if (cli.getNombre().isEmpty() || cli.getNombre() == null) {
            throw new miException("El nombre no puede estar vacio.");
        }

        if (cli.getApellido().isEmpty() || cli.getApellido() == null) {
            throw new miException("El apellido no puede estar vacio.");
        }

        if (cli.getDni() > 99999999) {
            throw new miException("El dni supera la cantidad de digitos maximos.");
        }

        if (cli.getDni() == null) {
            throw new miException("El dni no puede estar vacio.");
        }

        if (cli.getCorreo().isEmpty() || cli.getNombre() == null) {
            throw new miException("El correo no puede estar vacio.");
        }

        if (!cli.getCorreo().contains("@")) {
            throw new miException("El correo no contiene el simbolo '@'");
        }

        if (cli.getTelefono() < 0) {
            throw new miException("El numero ingresado es incorrecto.");
        }

        if (cli.getTelefono() == null) {
            throw new miException("El numero de telefono no puede estar vacio.");
        }

        if (cli.getPassword().isEmpty() || cli.getPassword() == null) {
            throw new miException("La password no puede estar incompleta.");
        }

        if (!isPasswordValid(cli.getPassword())) {
            throw new miException("La password no cumple con los requisitos de ser Alfanumerica y longitud minimo 6 caracteres");
        }

    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }

}
