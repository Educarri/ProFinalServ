/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.servicios.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin("*") //cualquier host puede consumir este controlador si pongo *
public class ProveedorControlador {

    @Autowired
    private ProveedorService proServ;

    @GetMapping
    public ResponseEntity<List<Proveedor>> findAllProveedores() {
        try {
            List<Proveedor> proveedores = proServ.listarProveedores();
            if (proveedores == null || proveedores.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.status(200).body(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Proveedor> guardarProveedor(@RequestBody Proveedor proveedor) {
        try {
            Proveedor pro = proServ.registrarProveedor(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(pro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable String id) {
        try {
            proServ.eliminarProveedor(id);
            return ResponseEntity.status(201).body("Proveedor Eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
