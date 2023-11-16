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
import ProyectoFinal.Final.servicios.AdminService;
import ProyectoFinal.Final.servicios.ClienteService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClienteService cliServ;

    @GetMapping("/dashboard")
    public String panelAdministrativo() {
        return "panelAdmin";
    }

    @GetMapping("/clientes")
    public String listarClientes(ModelMap modelo) {
        List<Cliente> clientes = cliServ.listarClientes();
        modelo.addAttribute("clientes", clientes);
        return "clientes_list";
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
