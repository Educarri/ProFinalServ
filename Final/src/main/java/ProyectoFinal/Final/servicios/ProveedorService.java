/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.enumeraciones.Rol;
import ProyectoFinal.Final.excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.ProveedorRepositorio;


@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepositorio proRepo;

    @Autowired
    private ImagenService imgService;

    @Transactional
    public Proveedor registrarProveedor(Proveedor prov) throws miException {
        validar(prov); // AGREGAR ATRIBUTO OFICIO Y PENSAR COMO HACER PARA QUE EL USUARIO SIN INGRESAR UN TIPO DE DATO ROL SE ASIGNE EL MISMO

        Proveedor prove = proRepo.buscarProveedorPorDNI(prov.getDNI());
        if (prove != null) {
            throw new miException("El dni ingresado de Proveedor ya está registrado.");
        }

        prov.setRol(Rol.PROVEEDOR);
        
        return proRepo.save(prov);

    }

    public List<Proveedor> listarProveedores() {

        return proRepo.findAll();

    }
    
        public Proveedor getOne(String id) {
        return proRepo.getOne(id);
    }

    @Transactional
    public void eliminarProveedor(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        proRepo.deleteById(id);

    }

    public void validar(Proveedor prov) throws miException {
        if (prov.getNombre().isEmpty() || prov.getNombre() == null) {
            throw new miException("El nombre no puede estar vacio.");
        }

        if (prov.getApellido().isEmpty() || prov.getApellido() == null) {
            throw new miException("El apellido no puede estar vacio.");
        }

        if (prov.getDNI() > 99999999) {
            throw new miException("El dni supera la cantidad de digitos maximos.");
        }

        if (prov.getDNI() == null) {
            throw new miException("El dni no puede estar vacio.");
        }

        if (prov.getCorreo().isEmpty() || prov.getNombre() == null) {
            throw new miException("El correo no puede estar vacio.");
        }

        if (!prov.getCorreo().contains("@")) {
            throw new miException("El correo no contiene el simbolo '@'");
        }

        if (prov.getTelefono() < 0) {
            throw new miException("El numero ingresado es incorrecto.");
        }

        if (prov.getTelefono() == null) {
            throw new miException("El numero de telefono no puede estar vacio.");
        }

        if (prov.getPassword().isEmpty() || prov.getPassword() == null) {
            throw new miException("La password no puede estar incompleta.");
        }

        if (!isPasswordValid(prov.getPassword())) {
            throw new miException("La password no cumple con los requisitos de ser Alfanumerica y longitud minimo 6 caracteres");
        }

        if(prov.getPrecioHs() == 0 || prov.getPrecioHs() < 0 || prov.getPrecioHs() == null){
            throw new miException("El valor del precio no puede ser <= 0 ni estar vacio.");
        }
        
        if(prov.getReputacion() < 0 || prov.getReputacion() == null){
            throw new miException("Debe ingresar un valor >= a cero.");
        }
        
        if(prov.getDescrService().length() < 20 || prov.getDescrService().isEmpty()){
            throw new miException("La descripcion debe ser de almenos 20 caracteres.");
        }
        
        //validar oficio
    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }
}
