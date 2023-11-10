/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

<<<<<<< HEAD:Final/src/main/java/servicios/TrabajoService.java
import entidades.Trabajo;
import excepciones.miException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorios.TrabajoRepositorio;

@Service
public class TrabajoService {

    @Autowired
    private TrabajoRepositorio traRepo;

    @Transactional
    public void registrarTrabajo(String IdCliente, String IdProveedor, Integer HsTrabajo, Integer presupuesto, String estado) throws miException {
        validar(IdCliente, IdProveedor, HsTrabajo, presupuesto, estado);

        Trabajo tra = new Trabajo();

        tra.setIdCliente(IdCliente);
        tra.setIdProveedor(IdProveedor);
        tra.setHsTrabajo(HsTrabajo);
        tra.setPresupuesto(presupuesto);
        tra.setEstado(estado);

        traRepo.save(tra);

    }

    @Transactional
    public void eliminarTrabajo(String id) throws miException {

        if (id == null || id.isEmpty()) {
            throw new miException("El id ingresado no es correcto");
        }

        traRepo.deleteById(id);

    }

    public void validar(String idCliente, String idProveedor, Integer hsTrabajo, Integer presupuesto, String estado) throws miException {

        if (idCliente.isEmpty()) {
            throw new miException("La id cliente esta vacia."); //necesario realizar algun metodo que realice la busqueda, y en caso de no encontrarlo muestre ese resultado como exception??
        }

        if (idProveedor.isEmpty()) {
            throw new miException("La id proveedor esta vacia.");
        }

        if (hsTrabajo < 0 || hsTrabajo == null) {
            throw new miException("Las horas minimas de trabajo son 1");
        }

        if (presupuesto < 0 || presupuesto == null) {
            throw new miException("Ingrese un monto valido.");
        }

        if (estado.length() < 10 || estado.isEmpty()) {
            throw new miException("La cantidad de caracteres minimos para la descripcion del estado son 10");
        }

    }
=======
/**
 *
 * @author gianc
 */
public class TrabajoControlador {
    
>>>>>>> ccf5e717874618087f7981d6918a61ea05a25247:Final/src/main/java/ProyectoFinal/Final/controladores/TrabajoControlador.java
}
