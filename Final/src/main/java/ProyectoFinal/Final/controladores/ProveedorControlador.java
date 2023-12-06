/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.servicios.ClienteService;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.TrabajoService;
import java.util.List;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private TrabajoService traServ;

    @Autowired
    private ClienteService cliServ;

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

    /*
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("proveedor", proServ.getOne(id));

        return "proveedor_modificar.html";
    }

     */
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
            @RequestParam(required = false) Integer precioHs,
            Integer reputacion,
            @RequestParam(required = false) String descripService,
            @RequestParam(required = false) MultipartFile archivo,
            ModelMap modelo,
            HttpSession sesion) {
        try {
            proServ.actualizarProveedor(nombre, apellido, dni, correo, telefono,
                    password, direccion, oficio, precioHs, descripService, archivo, id);
            sesion.setAttribute("error", null);
            sesion.setAttribute("exito", "Logro modificar correctamente al Proveedor");
            return "redirect:/inicio";
        } catch (miException e) {

            sesion.setAttribute("error", e.getMessage());
            return "redirect:/perfil";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/lista")
    public String listarProveedores(ModelMap modelo) {
        List<Proveedor> proveedores = proServ.listarProveedores();
        modelo.addAttribute("proveedores", proveedores);

        return "proveedor_lista.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            proServ.eliminarProveedor(id);
            modelo.put("exito", "Proveedor eliminado correctamente");
        } catch (miException e) {
            System.out.println("Error al eliminar");
            modelo.put("error", e.getMessage());

        }
        return "redirect:/proveedor/lista";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicioProveedor.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_USER')")
    @GetMapping("/contacto/{id}")
    public String contacto(@PathVariable String id, ModelMap modelo) {

        try {
            Proveedor pro = proServ.getOne(id);
            modelo.put("proveedor", pro);

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }
        return "contactoProveedor.html";
    }

    @PreAuthorize("hasRole('ROLE_PROVEEDOR')")
    @GetMapping("/listaTrabajos/{id}")
    public String listarTrabajos(@PathVariable String id, ModelMap modelo) {

        List<Trabajo> trabajos = traServ.listarTrabajosPorIdProveedor(id);
        modelo.addAttribute("trabajos", trabajos);

        return "listaTrabajosProveedor.html";
    }

    @GetMapping("/modificarRolProveedor/{id}")
    public String cambiarRolProveedor(@PathVariable String id, ModelMap modelo) {
        try {

            proServ.cambiarRol(id);

            Proveedor pro = proServ.getOne(id);

            Cliente cli = new Cliente();
            cli.setNombre(pro.getNombre());
            cli.setApellido(pro.getApellido());
            cli.setDni(pro.getDni());
            cli.setCorreo(pro.getCorreo());
            cli.setDireccion(pro.getDireccion());
            cli.setTelefono(pro.getTelefono());
            cli.setPassword(pro.getPassword());
            cli.setRol(pro.getRol());

            cliServ.registrarCambiado(cli);

            proServ.eliminarProveedor(id);

            modelo.put("exito", "Rol de Proveedor a Cliente modificado correctamente! Ingrese sus mismos datos para logearse");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/logout";
    }

    @GetMapping("/darseBaja/{id}")
    public String darseBaja(@PathVariable String id, ModelMap modelo) {
        try {

            proServ.darBaja(id);

            modelo.put("exito", "Proveedor dado de baja correctamente!");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/logout";
    }

}
