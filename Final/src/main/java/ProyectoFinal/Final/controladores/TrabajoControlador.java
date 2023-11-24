package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.excepciones.miException;
import ProyectoFinal.Final.servicios.ProveedorService;
import ProyectoFinal.Final.servicios.TrabajoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
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
            String estado, Integer calificacion, ModelMap modelo) {

        try {
            trabServ.registrarTrabajo(idCliente, idProveedor, HsTrabajo, presupuesto, estado, calificacion);

            modelo.put("exito", "El Trabajo fue guardado exitosamente");

        } catch (miException e) {
            modelo.put("error", e.getMessage());
            return "registroTrabajo.html";
        }

        return "index.html";
    }

    @GetMapping
    public ResponseEntity<List<Trabajo>> findAllTrabajos() {
        try {
            List<Trabajo> trabajos = trabServ.listarTrabajos();
            if (trabajos == null || trabajos.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.status(200).body(trabajos);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminarTrabajo(@PathVariable String id) {
        try {
            trabServ.eliminarTrabajo(id);
            return ResponseEntity.status(201).body("Trabajo Eliminado.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
