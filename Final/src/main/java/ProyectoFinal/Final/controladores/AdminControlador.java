package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Cliente;
<<<<<<< HEAD
<<<<<<< HEAD
import ProyectoFinal.Final.entidades.Proveedor;
=======
import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.enumeraciones.Rol;
>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======
import ProyectoFinal.Final.entidades.Imagen;
import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.enumeraciones.Rol;
>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.repositorios.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ProyectoFinal.Final.servicios.AdminService;
import ProyectoFinal.Final.servicios.ClienteService;
import ProyectoFinal.Final.servicios.ImagenService;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.UsuarioService;
import java.util.Date;
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

    @Autowired
    private ProveedorService proServ;
    
    @Autowired 
    private ImagenService imgServ;
    
    @Autowired
    private ImagenRepositorio imgRepo;

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

    /*
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/clientes")
    public String listarClientes(ModelMap modelo) {
        List<Cliente> clientes = cliServ.listarClientes();
        modelo.addAttribute("clientes", clientes);
        return "clientes_lista";
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
            adminService.modificarAdmin(nombre, apellido, dni, correo, telefono, password, direccion, id);
            modelo.put("exito", "Logro modificar correctamente al Administrador");
            return "panelAdmin.html";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "admin_modificar.html";
        }
    }

    @GetMapping("/modificarRolCliente/{id}")
    public String cambiarRolCliente(@PathVariable String id, ModelMap modelo) {
        try {
            cliServ.cambiarRol(id);
            Cliente cli = cliServ.getOne(id);
<<<<<<< HEAD
<<<<<<< HEAD
            
            Proveedor pro = new Proveedor();
            
=======

            Proveedor pro = new Proveedor();

>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======

            Proveedor pro = new Proveedor();

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            pro.setNombre(cli.getNombre());
            pro.setApellido(cli.getApellido());
            pro.setCorreo(cli.getCorreo());
            pro.setDireccion(cli.getDireccion());
            pro.setDni(cli.getDni());
            pro.setTelefono(cli.getTelefono());
<<<<<<< HEAD
<<<<<<< HEAD
            pro.setRol(cli.getRol());
=======
            pro.setRol(Rol.PROVEEDOR);
>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            pro.setDescripService("-");
            Imagen imagenPorDefecto = imgServ.obtenerImagenPorDefecto();
            imgRepo.save(imagenPorDefecto);
            pro.setImagen(imagenPorDefecto);
            pro.setOficio(null);
            pro.setPrecioHs(1);
            pro.setPassword(cli.getPassword());
            pro.setCalificacionPromedio(0.0);
            pro.setNumeroCalificaciones(0);
            pro.setFechaCreacion(new Date());

            proServ.registrarCambiado(pro);

            cliServ.eliminarCliente(id);
<<<<<<< HEAD
          
            
           
=======
            pro.setRol(Rol.PROVEEDOR);
            pro.setDescripService("-");
            Imagen imagenPorDefecto = imgServ.obtenerImagenPorDefecto();
            imgRepo.save(imagenPorDefecto);
            pro.setImagen(imagenPorDefecto);
            pro.setOficio(null);
            pro.setPrecioHs(1);
            pro.setPassword(cli.getPassword());
            pro.setCalificacionPromedio(0.0);
            pro.setNumeroCalificaciones(0);
            pro.setFechaCreacion(new Date());

            proServ.registrarCambiado(pro);

            cliServ.eliminarCliente(id);

>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            modelo.put("exito", "Rol de Cliente a Proveedor modificado correctamente!");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/cliente/lista";
    }

    @GetMapping("/modificarRolProveedor/{id}")
    public String cambiarRolProveedor(@PathVariable String id, ModelMap modelo) {
        try {
<<<<<<< HEAD
<<<<<<< HEAD
            
=======

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            proServ.cambiarRol(id);

            Proveedor pro = proServ.getOne(id);
<<<<<<< HEAD
            
=======

            proServ.cambiarRol(id);

            Proveedor pro = proServ.getOne(id);

>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            Cliente cli = new Cliente();
            cli.setNombre(pro.getNombre());
            cli.setApellido(pro.getApellido());
            cli.setDni(pro.getDni());
            cli.setCorreo(pro.getCorreo());
            cli.setDireccion(pro.getDireccion());
            cli.setTelefono(pro.getTelefono());
            cli.setPassword(pro.getPassword());
<<<<<<< HEAD
<<<<<<< HEAD
            cli.setRol(pro.getRol());
            
=======
            cli.setRol(Rol.USER);

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            cliServ.registrarCambiado(cli);

            proServ.eliminarProveedor(id);
<<<<<<< HEAD
            
            
=======
            cli.setRol(Rol.USER);

            cliServ.registrarCambiado(cli);

            proServ.eliminarProveedor(id);

>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            modelo.put("exito", "Rol de Proveedor a Cliente modificado correctamente!");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
        }
        return "redirect:/proveedor/lista";
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