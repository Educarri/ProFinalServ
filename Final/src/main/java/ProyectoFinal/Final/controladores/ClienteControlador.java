/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.repositorios.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.ClienteService;
import ProyectoFinal.Final.servicios.ImagenService;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.TrabajoService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private ImagenService imgServ;

    @Autowired
    private ImagenRepositorio imgRepo;

    @Autowired
    private ProveedorService proServ;

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
            ModelMap modelo,
            HttpSession sesion) {
        try {
            cliServ.modificarCliente(nombre, apellido, dni, correo, telefono, password, direccion, id);
            sesion.setAttribute("error", null);
            sesion.setAttribute("exito", "Logro modificar correctamente al Cliente");
            return "redirect:/inicio";
        } catch (miException e) {

            sesion.setAttribute("error", e.getMessage());
            return "redirect:/perfil";
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

    @GetMapping("/modificarRolCliente/{id}")
    public String cambiarRolCliente(@PathVariable String id, ModelMap modelo) {
        try {
            cliServ.cambiarRol(id);
            Cliente cli = cliServ.getOne(id);

            Proveedor pro = new Proveedor();

            pro.setNombre(cli.getNombre());
            pro.setApellido(cli.getApellido());
            pro.setCorreo(cli.getCorreo());
            pro.setDireccion(cli.getDireccion());
            pro.setDni(cli.getDni());
            pro.setTelefono(cli.getTelefono());
            pro.setRol(cli.getRol());
            pro.setDescripService("-");
            Imagen imagenPorDefecto = imgServ.obtenerImagenPorDefecto();
            imgRepo.save(imagenPorDefecto);
            pro.setImagen(imagenPorDefecto);
            pro.setOficio(null);
            pro.setPrecioHs(1);
            pro.setPassword(cli.getPassword());
            pro.setCalificacionPromedio(0);
            pro.setNumeroCalificaciones(0);
            pro.setFechaCreacion(new Date());

            proServ.registrarCambiado(pro);

            cliServ.eliminarCliente(id);

            modelo.put("exito", "Rol de Cliente a Proveedor modificado correctamente! Ingresa tus mismos datos para logearte");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/logout";
    }

    @GetMapping("/darseBaja/{id}")
    public String darseBaja(@PathVariable String id, ModelMap modelo) {
        try {

            cliServ.darBaja(id);

            modelo.put("exito", "Cliente dado de baja correctamente!");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/logout";
    }
}
