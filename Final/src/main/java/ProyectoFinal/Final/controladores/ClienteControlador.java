/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.ClienteService;
import ProyectoFinal.Final.servicios.TrabajoService;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {

    @Autowired
    private ClienteService cliServ;
    
    @Autowired
    private TrabajoService traServ;

    @PreAuthorize("permitAll()")
    @GetMapping("/registrar")
    public String registrar() {
        return "registroCliente.html";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, @RequestParam(required = false) String apellido, @RequestParam(required = false) Long dni,
            String correo, Integer telefono, String password, String direccion, ModelMap modelo) {

        try {
            cliServ.registrarCliente(nombre, apellido, dni, correo, telefono, password, direccion);
            modelo.put("exito", "El Cliente fue guardado exitosamente");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
            return "registroCliente.html"; //si hay error se regarga la misma pagina
        }

        return "index.html"; //si no hay errores me manda a la pagina main
    }

    /*
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("user", cliServ.getOne(id));

        return "cliente_modificar.html";
    }

*/
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
            cliServ.modificarCliente(nombre, apellido, dni, correo, telefono, password, direccion, id);
            modelo.put("exito", "Logro modificar correctamente al Cliente");
            return "inicio.html";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "cliente_modificar.html";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public String listarClientes(ModelMap modelo) {
        List<Cliente> clientes = cliServ.listarClientes();
        modelo.addAttribute("clientes", clientes);

        return "clientes_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            cliServ.eliminarCliente(id);
            modelo.put("exito", "Cliente eliminado correctamente");
        } catch (miException e) {
            System.out.println("Error al eliminar");
            modelo.put("error", e.getMessage());

        }
        return "redirect:/cliente/lista";
    }
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/listaTrabajos/{id}")
    public String listarTrabajos(@PathVariable String id, ModelMap modelo) {
        
        List<Trabajo> trabajos = traServ.listarTrabajosPorIdCliente(id);
        modelo.addAttribute("trabajos", trabajos);

        return "listaTrabajosCliente.html";
    }


}
