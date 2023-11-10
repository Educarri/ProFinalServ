/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.enumeraciones;

<<<<<<< HEAD:Final/src/main/java/servicios/ProveedorService.java
import entidades.Imagen;
import entidades.Proveedor;
import excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repositorios.ProveedorRepositorio;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepositorio proRepo;

    @Autowired
    private ImagenService imgService;

    @Transactional
    public void registrarProveedor(MultipartFile Archivo, String nombre, String apellido,
            Long dni, String correo, Integer telefono, String password,
            String direccion, Integer precioHs, Integer reputacion, String descripcionService) throws miException {
        validar(nombre, apellido, dni, correo, telefono, password, direccion, precioHs, reputacion, descripcionService); // AGREGAR ATRIBUTO OFICIO Y PENSAR COMO HACER PARA QUE EL USUARIO SIN INGRESAR UN TIPO DE DATO ROL SE ASIGNE EL MISMO

        Proveedor pro = new Proveedor();

        pro.setNombre(nombre);
        pro.setApellido(apellido);
        pro.setCorreo(correo);
        pro.setPassword(password);
        pro.setDireccion(direccion);
        pro.setDNI(dni);
        pro.setTelefono(telefono);

        Imagen imagen = imgService.guardar(Archivo);

        pro.setImagen(imagen);

        proRepo.save(pro);

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

    public void validar(String nombre, String apellido, Long dni, String correo, Integer telefono, String password, String direccion, Integer precioHs, Integer reputacion, String descripcionService) throws miException {
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

        if(precioHs == 0 || precioHs < 0 || precioHs == null){
            throw new miException("El valor del precio no puede ser <= 0 ni estar vacio.");
        }
        
        if(reputacion < 0 || reputacion == null){
            throw new miException("Debe ingresar un valor >= a cero.");
        }
        
        if(descripcionService.length() < 20 || descripcionService.isEmpty()){
            throw new miException("La descripcion debe ser de almenos 20 caracteres.");
        }
        
        //validar oficio
    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }
=======
/**
 *
 * @author gianc
 */
public enum Rol {
    ADMIN, USER, PROVEEDOR
>>>>>>> ccf5e717874618087f7981d6918a61ea05a25247:Final/src/main/java/ProyectoFinal/Final/enumeraciones/Rol.java
}
