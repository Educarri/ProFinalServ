/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
import ProyectoFinal.Final.excepciones.miException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.AdminService;
import ProyectoFinal.Final.servicios.ClienteService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClienteService cliServ;

    @GetMapping("/registrar")
    public String registrar() {
        return "registroCliente.html";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) Long dni,
            String correo, Integer telefono, String password, String direccion, ModelMap modelo) {

        try {
            adminService.registrarAdmin(nombre, apellido, dni, correo, telefono, password, direccion);
            modelo.put("exito", "El Admin fue guardado exitosamente");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
            return "registroCliente.html"; //si hay error se regarga la misma pagina
        }

        return "index.html"; //si no hay errores me manda a la pagina main
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panelAdmin";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clientes")
    public String listarClientes(ModelMap modelo) {
        List<Cliente> clientes = cliServ.listarClientes();
        modelo.addAttribute("clientes", clientes);
        return "clientes_list";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Long dni,
            @RequestParam(required = false) String correo,
            @RequestParam(required = false) Integer telefono,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String direccion,
            ModelMap modelo) {
        try {
            adminService.modificarAdmin(nombre, apellido, dni, correo, telefono, password, direccion, id);
            modelo.put("exito", "Logro modificar correctamente al Administrador");
            return "panelAdmin.html";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "admin_modificar.html";
        }
    }

    @PostMapping("/eliminar{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            cliServ.eliminarCliente(id);
            modelo.put("exito", "Usuario eliminado correctamente.");
            return "clientes_list";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "clientes_list";
        }
    }
}
