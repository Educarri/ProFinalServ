/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepositorio extends JpaRepository<Trabajo, String>{
    
       @Query("SELECT t FROM Trabajo t WHERE t.estado = :estado")
    public Trabajo buscarTrabajoPorEstado(@Param("estado")String estado);
    
         @Query("SELECT t FROM Trabajo t WHERE t.calificacion = :calificacion")
    public Trabajo buscarTrabajoPorCalificacion(@Param("calificacion")String calificacion);
    
}
