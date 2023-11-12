
package ProyectoFinal.Final.controladores;


import ProyectoFinal.Final.entidades.Trabajo;
import ProyectoFinal.Final.servicios.TrabajoService;
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
@RequestMapping("/trabajo")
@CrossOrigin("*") //cualquier host puede consumir este controlador si pongo *
public class TrabajoControlador {

    @Autowired
    private TrabajoService trabServ;

    @PostMapping
    public ResponseEntity<Trabajo> crearTrabajo(@RequestBody Trabajo trabajo) {

        try {
            Trabajo t = trabServ.registrarTrabajo(trabajo);
            return ResponseEntity.status(HttpStatus.CREATED).body(t);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

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
