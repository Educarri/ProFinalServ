/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.servicios;

import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProyectoFinal.Final.repositorios.TrabajoRepositorio;
import java.util.List;


@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepositorio traRepo;

    @Transactional
    public Trabajo registrarTrabajo(Trabajo trabajo) throws miException{

        validar(trabajo);
        return traRepo.save(trabajo);

    }

    @Transactional
    public void eliminarTrabajo(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        traRepo.deleteById(id);

    }
    
      public List<Trabajo> listarTrabajos() {

        return traRepo.findAll();

    }

    public void validar(Trabajo trabajo) throws miException {

        if (trabajo.getIdCliente().isEmpty()) {
            throw new miException("La id cliente esta vacia."); //necesario realizar algun metodo que realice la busqueda, y en caso de no encontrarlo muestre ese resultado como exception??
        }

        if (trabajo.getIdProveedor().isEmpty()) {
            throw new miException("La id proveedor esta vacia.");
        }

        if (trabajo.getHsTrabajo() < 0 || trabajo.getHsTrabajo() == null) {
            throw new miException("Las horas minimas de trabajo son 1");
        }

        if (trabajo.getPresupuesto() < 0 || trabajo.getPresupuesto() == null) {
            throw new miException("Ingrese un monto valido.");
        }

        if (trabajo.getEstado().length() < 10 || trabajo.getEstado().isEmpty()) {
            throw new miException("La cantidad de caracteres minimos para la descripcion del estado son 10");
        }

    }
}