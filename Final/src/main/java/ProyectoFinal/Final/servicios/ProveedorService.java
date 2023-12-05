/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.enumeraciones.Oficios;
import ProyectoFinal.Final.enumeraciones.Rol;
import ProyectoFinal.Final.excepciones.miException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.ProveedorRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepositorio proRepo;

    @Autowired
    private ImagenService imgService;
    
    @Autowired
    private TrabajoService traserv;

    @Transactional
    public void registrarProveedor(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion, String oficio, Integer precioHs,
            String descripService, MultipartFile archivo) throws miException {

        validar(nombre, apellido, dni, correo, telefono, password, direccion, oficio, precioHs, descripService, archivo); // AGREGAR ATRIBUTO OFICIO Y PENSAR COMO HACER PARA QUE EL USUARIO SIN INGRESAR UN TIPO DE DATO ROL SE ASIGNE EL MISMO

        Proveedor prove = proRepo.buscarProveedorPorDNI(dni);
        if (prove != null) {
            throw new miException("El dni ingresado de Proveedor ya está registrado.");
        }

        Proveedor pro = new Proveedor();
        pro.setNombre(nombre);
        pro.setApellido(apellido);
        pro.setDni(dni);
        pro.setCorreo(correo);
        pro.setTelefono(telefono);
        pro.setPassword(new BCryptPasswordEncoder().encode(password));
        pro.setDireccion(direccion);
        pro.setCalificacionPromedio(0);
        pro.setNumeroCalificaciones(0);
        pro.setFechaCreacion(new Date());

        Imagen imagen = imgService.guardar(archivo);
        pro.setImagen(imagen);

        switch (oficio.toLowerCase()) {
            case "albañil":
                pro.setOficio(Oficios.ALBAÑIL);
                break;
            case "gasista":
                pro.setOficio(Oficios.GASISTA);
                break;
            case "plomero":
                pro.setOficio(Oficios.PLOMERO);
                break;
            case "electricista":
                pro.setOficio(Oficios.ELECTRICISTA);
                break;
        }

        pro.setPrecioHs(precioHs);
        pro.setDescripService(descripService);
        pro.setRol(Rol.PROVEEDOR);

        proRepo.save(pro);

    }
    
   
    @Transactional
    public void registrarCambiado(Proveedor pro){
        if(pro != null){
            proRepo.save(pro);
        }
    }

    @Transactional
    public void actualizarProveedor(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion, String oficio,
            Integer precioHs, Integer reputacion, String descripService, MultipartFile archivo,
            String id) throws miException {

        //  validar(nombre, apellido, dni, correo, telefono, password, direccion, oficio, precioHs, descripService, archivo);
        Optional<Proveedor> respuesta = proRepo.findById(id);

        if (respuesta.isPresent()) {
            Proveedor pr = respuesta.get();
            pr.setNombre(nombre);
            pr.setCorreo(correo);
            pr.setPassword(new BCryptPasswordEncoder().encode(password));
            pr.setRol(Rol.PROVEEDOR);
            pr.setDireccion(direccion);
            pr.setDescripService(descripService);
            pr.setPrecioHs(precioHs);
            pr.setCalificacionPromedio(pr.getCalificacionPromedio());

            System.out.println("Precio hora dentro modificar " + precioHs);

            String idImagen = null;

            if (pr.getImagen() != null) {
                idImagen = pr.getImagen().getId();
            }

            Imagen img = null;

            if (archivo != null && !archivo.isEmpty()) {
                img = imgService.modificarImagen(archivo, idImagen);
            }

            pr.setImagen(img);

            proRepo.save(pr);
        }

    }

    @Transactional
    public void cambiarRol(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("La identificacion del Proveedor no es correcta.");
        }

        Optional<Proveedor> respuesta = proRepo.findById(id);

        if (respuesta.isPresent()) {
            Proveedor user = respuesta.get();

            if (user.getRol().equals(Rol.PROVEEDOR)) {
                user.setRol(Rol.USER);
            }else{
                user.setRol(Rol.PROVEEDOR);
            }
        }
    }
    
    
    @Transactional
    public void darBaja(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("La identificacion del Proveedor no es correcta.");
        }

        Optional<Proveedor> respuesta = proRepo.findById(id);

        if (respuesta.isPresent()) {
            Proveedor user = respuesta.get();

            user.setRol(Rol.BAJA);
        }
    }

    public List<Proveedor> listarProveedores() {

        return proRepo.findAll();

    }

    public Proveedor getOne(String id) {
        return proRepo.getOne(id);
    }

    public String obtenerNombreProveedor(String id){
       
        String nombre = null;
        
         Optional<Proveedor> respuesta = proRepo.findById(id);
         
         if(respuesta != null){
             Proveedor pro = respuesta.get();
             nombre = pro.getNombre();
         }
        
        return nombre;
    }
    
   
    @Transactional
    public void eliminarProveedor(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        proRepo.deleteById(id);

    }

    public void validar(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password,
            String direccion, String oficio, Integer precioHs,
            String descripService, MultipartFile archivo) throws miException {
        if (nombre.isEmpty()) {
            throw new miException("El nombre no puede estar vacio.");
        }

        if (apellido.isEmpty()) {
            throw new miException("El apellido no puede estar vacio.");
        }

        if (dni > 99999999) {
            throw new miException("El dni supera la cantidad de digitos maximos.");
        }

        if (dni == null) {
            throw new miException("El dni no puede estar vacio.");
        }

        if (correo.isEmpty()) {
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

        if (password.isEmpty()) {
            throw new miException("La password no puede estar incompleta.");
        }

        if (!isPasswordValid(password)) {
            throw new miException("La password no cumple con los requisitos de ser Alfanumerica y longitud minimo 6 caracteres");
        }

        if (precioHs == 0 || precioHs < 0) {
            throw new miException("El valor del precio no puede ser <= 0 ni estar vacio.");
        }

        if (descripService.length() < 20 || descripService.isEmpty()) {
            throw new miException("La descripcion debe ser de almenos 20 caracteres.");
        }

        if (oficio.toLowerCase() != "gasista" && oficio.toLowerCase() == "electricista" && oficio.toLowerCase() == "plomero" && oficio.toLowerCase() == "albañil") {
            throw new miException("El oficio ingresado no corresponde con los disponibles.");
        }

        if (archivo == null || archivo.isEmpty()) {
            throw new miException("La imagen no puede estar vacia.");
        }
    }

    public boolean isPasswordValid(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?!\\s)[a-zA-Z0-9]{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();

    }
   public void calificarProveedor(String idTrabajo, Integer calificacion) throws miException {
       
       Trabajo tra = traserv.getOne(idTrabajo);
       String idProveedor = tra.getIdProveedor();
    Optional<Proveedor> optionalProveedor = proRepo.findById(idProveedor);
    
       
    if (optionalProveedor.isPresent()) {
        Proveedor proveedor = optionalProveedor.get();
        
        Integer nuevoPromedio = ((proveedor.getCalificacionPromedio() * proveedor.getNumeroCalificaciones()) + calificacion)
                / (proveedor.getNumeroCalificaciones() + 1);

        proveedor.setCalificacionPromedio(nuevoPromedio);
        proveedor.setNumeroCalificaciones(proveedor.getNumeroCalificaciones() + 1);

        proRepo.save(proveedor);
    } else {
        throw new miException("Proveedor no encontrado");
    }
}
}
