/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.repositorios;

import ProyectoFinal.Final.entidades.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositorio extends JpaRepository<Calificacion, String> {
    
         @Query("SELECT c FROM Calificacion c WHERE c.puntaje = :puntaje")
    public Calificacion buscarCalificacionPorPuntaje(@Param("puntaje")Integer puntaje);
}
