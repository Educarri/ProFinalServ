package ProyectoFinal.Final.controladores;

import ProyectoFinal.Final.entidades.Proveedor;
import ProyectoFinal.Final.servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/imagen")
@CrossOrigin("*") //cualquier host puede consumir este controlador si pongo *
public class ImagenControlador {

    @Autowired
    private ProveedorService proServ;
    
    
    @PreAuthorize("permitAll()")
    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenProveedor(@PathVariable String id) {

        Proveedor pro = proServ.getOne(id);

        byte[] imagen = pro.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}