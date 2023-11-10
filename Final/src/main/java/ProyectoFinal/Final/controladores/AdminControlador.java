/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Administrador;
import ProyectoFinal.Final.entidades.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.AdminService;
import ProyectoFinal.Final.servicios.ClienteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*") //cualquier host puede consumir este controlador si pongo *
public class AdminControlador {
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private ClienteService cliServ;
    
    
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> findAllClientes() {
        try {
            List<Cliente> clientes = cliServ.listarClientes();
            if (clientes == null || clientes.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.status(200).body(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    
        @GetMapping("/cliente/DNI/{DNI}")
    public ResponseEntity<Cliente> buscarClientePorDNI(@PathVariable Long DNI) {
        try {
            Cliente cli = cliServ.buscarClientePorDNI(DNI);
            return ResponseEntity.status(200).body(cli);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
    
    
    @PostMapping
    public ResponseEntity<Administrador> save(@RequestBody Administrador admin) {
        try {
            Administrador ad = adminService.registrarAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(ad);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
