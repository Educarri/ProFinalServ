/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.repositorios;

import ProyectoFinal.Final.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProveedorRepositorio extends JpaRepository<Proveedor, String>{
    
       @Query("SELECT p FROM Proveedor p WHERE p.correo = :correo")
    public Proveedor buscarProveedorPorEmail(@Param("correo")String correo);
    
         @Query("SELECT p FROM Proveedor p WHERE p.nombre = :nombre")
    public Proveedor buscarProveedorPorNombre(@Param("nombre")String nombre);
    
              @Query("SELECT c FROM Cliente c WHERE c.dni = :dni")
    public Proveedor buscarProveedorPorDNI(@Param("dni")Long dni);
    
}
