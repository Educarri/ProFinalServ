/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.repositorios.ProveedorRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.TrabajoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepositorio traRepo;

    @Autowired
    private ProveedorRepositorio proRepo;

    @Transactional
    public void registrarTrabajo(String idCliente, String idProveedor, Integer HsTrabajo, Integer presupuesto,
            String estado, Integer calificacion, String comentario) throws miException {

        validar(HsTrabajo);

        Trabajo tra = new Trabajo();

        Proveedor pro = proRepo.getOne(idProveedor);

        Integer precioHoraProveedor = pro.getPrecioHs();

        Integer valorFinalHora = precioHoraProveedor * HsTrabajo;

        tra.setIdCliente(idCliente);
        tra.setIdProveedor(idProveedor);
        tra.setPresupuesto(valorFinalHora);
        tra.setEstado(estado);
        tra.setHsTrabajo(HsTrabajo);
        tra.setFechaCreacion(new Date());
        tra.setCalificacion(calificacion);
        tra.setComentario("");
        tra.setAceptado(false);

        traRepo.save(tra);

    }

    public Trabajo getOne(String id) {
        return traRepo.getOne(id);
    }

    @Transactional
    public void eliminarTrabajo(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        traRepo.deleteById(id);

    }

    @Transactional
    public void eliminarComentario(String id) throws miException {

        Optional<Trabajo> respuesta = traRepo.findById(id);

        if (respuesta.isPresent()) {
            Trabajo tra = respuesta.get();

            tra.setComentario("**********");
            traRepo.save(tra);
        }

    }

    public List<Trabajo> listarTrabajos() {

        return traRepo.findAll();

    }

    public List<Trabajo> listarTrabajosPorIdCliente(String id) {

        return traRepo.buscarTrabajoPorIdCliente(id);
    }

    public List<Trabajo> listarTrabajosPorIdProveedor(String id) {

        return traRepo.buscarTrabajoPorIdProveedor(id);
    }

    @Transactional
    public void modificar(String id,
            Integer calificacion, String comentario) throws miException {

        if(comentario.isEmpty() || comentario.length() < 3){
            throw new miException("Error en la carga de la calificacion.");
        }
           
        Optional<Trabajo> respuesta = traRepo.findById(id);

            
        if (respuesta.isPresent()) {
            Trabajo tra = respuesta.get();

            if (calificacion == null) {
                calificacion = tra.getCalificacion(); 
            }

            tra.setCalificacion(calificacion);

            tra.setComentario(comentario);

            traRepo.save(tra);
        }
    }

    @Transactional
    public void cambiarEstado(String id,
            String estado) throws miException {

        Optional<Trabajo> respuesta = traRepo.findById(id);

        if (respuesta.isPresent()) {
            Trabajo tra = respuesta.get();

            tra.setEstado(estado);

            traRepo.save(tra);
        }
    }

    @Transactional
    public void rechazarTrabajo(String id) throws miException {

        Optional<Trabajo> respuesta = traRepo.findById(id);

        if (respuesta.isPresent()) {
            Trabajo tra = respuesta.get();

            tra.setEstado("Rechazado");

            traRepo.save(tra);
        }
    }

    @Transactional
    public void cambiarAceptado(String id,
            Boolean aceptado) throws miException {

        Optional<Trabajo> respuesta = traRepo.findById(id);

        if (respuesta.isPresent()) {
            Trabajo tra = respuesta.get();

            tra.setAceptado(aceptado);

            traRepo.save(tra);
        }
    }

    public void validar(Integer HsTrabajo) throws miException {

        if (HsTrabajo < 0 || HsTrabajo == null) {

            throw new miException("Ingrese una cantidad de horas razonable.");
        }

    }

}
