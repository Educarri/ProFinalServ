/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.servicios.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {

    @Autowired
    private ProveedorService proServ;

    @PreAuthorize("permitAll()")
    @GetMapping("/registrar")
    public String registrar() {
        return "registroProveedor.html";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/registro")
    public String registro(String nombre, String apellido, Long dni,
            String correo, Integer telefono, String password, String direccion, String oficio, Integer precioHs,
            String descripService, MultipartFile archivo, ModelMap modelo) {

        try {
            proServ.registrarProveedor(nombre, apellido, dni, correo, telefono,
                    password, direccion, oficio, precioHs, descripService, archivo);

            modelo.put("exito", "El Proveedor fue guardado exitosamente");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
            return "registroProveedor.html"; //si hay error se regarga la misma pagina
        }

        return "index.html"; //si no hay errores me manda a la pagina main
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("proveedor", proServ.getOne(id));

        return "proveedor_modificar.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Long dni,
            @RequestParam(required = false) String correo,
            @RequestParam(required = false) Integer telefono,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String direccion,
            @RequestParam(required = false) String oficio,
            @RequestParam(required = false) Integer PrecioHs,
            @RequestParam(required = false) Integer reputacion,
            @RequestParam(required = false) String descripService,
            @RequestParam(required = false) MultipartFile archivo,
            ModelMap modelo) {
        try {
            proServ.actualizarProveedor(nombre, apellido, dni, correo, telefono,
                    password, direccion, oficio, PrecioHs, reputacion, descripService, archivo, id);
            modelo.put("exito", "Logro modificar correctamente al Proveedor");
            return "redirect:../lista";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "proveedor_modificar.html";
        }
    }

    @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo) {
        List<Proveedor> proveedores = proServ.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);

        return "proveedores_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public void eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            proServ.eliminarProveedor(id);
            modelo.put("exito", "Proveedor eliminado correctamente");
        } catch (miException e) {
            System.out.println("Error al eliminar");
            modelo.put("error", e.getMessage());

        }
    }

    
    @GetMapping("/inicio")
    public String inicio(){
        return "inicioProveedor.html";
    }

}
