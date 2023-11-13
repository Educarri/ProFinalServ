/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*") //cualquier host puede consumir este controlador si pongo *
public class ClienteControlador {
    
    @Autowired
    private ClienteService cliServ;
    
    
      
    @PostMapping
    public ResponseEntity<Object> crearCliente(@RequestBody Cliente cli) {
        try {
            Cliente c = cliServ.registrarCliente(cli);
            return ResponseEntity.status(HttpStatus.CREATED).body(c);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarCliente(@PathVariable String id) {
        try {
            cliServ.eliminarCliente(id);
            return ResponseEntity.status(201).body("Cliente Eliminado.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Object> modificarCliente(@PathVariable String id, @RequestBody Cliente cli) {
        try {
            Cliente cl = cliServ.modificarCliente(id, cli);
            return ResponseEntity.status(301).body(cl);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
    @GetMapping("/buscar/{dni}")
    public ResponseEntity<Cliente> BuscarClientePorDNI(@PathVariable Long DNI) {
        try {
            Cliente cliente = cliServ.buscarClientePorDNI(DNI);
            return ResponseEntity.status(201).body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    
        @GetMapping("/id/{id}")
    public ResponseEntity<Cliente> buscarClientePorID(@PathVariable String id) {
        try {
            Cliente cli = cliServ.buscarClientePorID(id);
            return ResponseEntity.status(200).body(cli);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    
}
