/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorios;

import entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
      @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente buscarClientePorEmail(@Param("email")String email);
    
         @Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
    public Cliente buscarClientePorNombre(@Param("nombre")String nombre);
    
}
