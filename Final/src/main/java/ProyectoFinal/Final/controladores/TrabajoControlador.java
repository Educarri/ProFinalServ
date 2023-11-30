package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.TrabajoService;
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

@Controller
@RequestMapping("/trabajo")
public class TrabajoControlador {

    @Autowired
    private TrabajoService trabServ;

    @Autowired
    private ProveedorService proServ;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/contratar/{id}")
    public String registrar(@PathVariable String id, ModelMap modelo) {

        try {
            Proveedor pro = proServ.getOne(id);
            modelo.put("proveedor", pro);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }

        return "registroTrabajo.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String idCliente, String idProveedor, Integer HsTrabajo, Integer presupuesto,
            String estado, Integer calificacion, String comentario, ModelMap modelo) {

        try {
            trabServ.registrarTrabajo(idCliente, idProveedor, HsTrabajo, presupuesto, estado, calificacion, comentario);

            modelo.put("exito", "El Trabajo fue guardado exitosamente");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
            return "registroTrabajo.html";
        }

        return "index.html";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/listaTrabajos")
    public String listarTrabajos(ModelMap modelo) {
        List<Trabajo> trabajos = trabServ.listarTrabajos();
        modelo.addAttribute("trabajos", trabajos);

        return "listaTrabajosAdmin.html";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/calificar/{id}")
    public String calificar(@PathVariable String id, ModelMap modelo) {

        try {
            Trabajo trabajo = trabServ.getOne(id);
            if (trabajo.getEstado().toLowerCase().equals("finalizado")) {
                modelo.addAttribute("trabajo", trabajo);
                 return "calificarTrabajo.html";
            }else{
                modelo.put("error", "El trabajo todavia no fue finalizado, intente mas tarde.");
            }
            
            
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }  
       return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String calificado(@PathVariable String id,
            String estado, Integer calificacion, String comentario,
            ModelMap modelo) {

        try {
<<<<<<< HEAD
<<<<<<< HEAD
            trabServ.modificar(id, estado, calificacion, comentario);
            modelo.put("exito", "Logro modificar correctamente al Trabajo");
       
=======
            trabServ.modificar(id, calificacion, comentario);
            modelo.put("exito", "Logro modificar correctamente al Trabajo");

>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======
            trabServ.modificar(id, calificacion, comentario);
            modelo.put("exito", "Logro modificar correctamente al Trabajo");

>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            proServ.calificarProveedor(id, calificacion);
            return "redirect:/inicio";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "calificarTrabajo.html";
        }

    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/estado/{id}")
    public String estado(@PathVariable String id, ModelMap modelo) {

        try {
            Trabajo trabajo = trabServ.getOne(id);
            modelo.addAttribute("trabajo", trabajo);

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }

        return "cambiarEstadoTrabajo.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @PostMapping("/cambiar/{id}")
    public String cambiarEstado(@PathVariable String id,
<<<<<<< HEAD
<<<<<<< HEAD
            String estado, 
=======
            String estado,
>>>>>>> b216b020352c169027d44315cc5220324d97eaad
=======
            String estado,
>>>>>>> d176ffd3d1a1e2681a4487c2b5d5a47a699e688c
            ModelMap modelo) {

        try {
            trabServ.cambiarEstado(id, estado);
            modelo.put("exito", "Logro cambiar el estado correctamente al Trabajo");
            return "redirect:/inicio";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "cambiarEstadoTrabajo.html";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/eliminarComentario/{id}")
    public String cambiarEstado(@PathVariable String id,
            ModelMap modelo) {

        try {
            trabServ.eliminarComentario(id);
            modelo.put("exito", "Logro eliminar el Comentario correctamente del Trabajo");
            return "redirect:/inicio";
        } catch (miException e) {

            modelo.put("error", e.getMessage());
            return "listaTrabajosAdmin.html";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id, ModelMap modelo) {
        try {
            trabServ.eliminarTrabajo(id);
            modelo.put("exito", "Trabajo eliminado correctamente");
        } catch (miException e) {
            System.out.println("Error al eliminar");
            modelo.put("error", e.getMessage());

        }
        return "redirect:/inicio";
    }

}